package net.pi.pimodule.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import home.common.data.LogMessage;
import home.common.data.LogOption;
import net.pi.pimodule.service.model.Message;

/**
 * Service to generate debug logs..
 * 
 * format
 * 	Input will be JSON
 * http://localhost:8080/web/logger/initAppLog/pimod/FILE_FIXED_SIZE
 *	http://localhost:8080/web/logger/log
 * 
 *  {level: 'debug', class':'className', 'errorMessage':'broken please fix', 'fullErrorStack': 'full error stack ex', logFileName: 'fileName'}
 *  
 *  option : 
 *  	FILE_FIXED_SIZE = 15 meg file size
 *  	FILE_UNLIMITED = no limit to megs
 * 
 * @author ADMIN
 *
 */
@Path("logger")
public class LoggerService {

	private static final Logger logger = LogManager.getLogger(LoggerService.class);
	DateTimeFormatter iso_8601_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
	DateTimeFormatter logFileDateMessage = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm.ss");
	private String logPath = "."+File.separatorChar + "logs" + File.separatorChar;

	private Gson gson;

	public LoggerService() {
		gson = new Gson();
	}

	@Path("initAppLog/{fileName}/{option}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response initLog(@PathParam("fileName") String logFileName, @PathParam("option") String logOption) {
		Message msg = null;

		Status status = Status.BAD_REQUEST;

		if ( logFileName == null || logFileName.trim().length() == 0 ) {
			status = Status.BAD_REQUEST;
			msg = new Message("File Name missing", "Please provide file name");
			return Response.status(status).entity(msg).build();
		}

		boolean optionValid = false;

		//create file
		File logFile = null;

		try {
			LogOption logOpt  = LogOption.valueOf(logOption);
			optionValid = true;			

			if (logOpt == LogOption.FILE_FIXED_SIZE || logOpt == LogOption.FILE_UNLIMITED) {
				LocalDateTime start = LocalDateTime.now();
				String nowDateTime = start.format(iso_8601_formatter);
				logFile = new File( logPath + logFileName + "-" + nowDateTime + ".log");
			}else {
				logFile = new File( logPath + logFileName + ".log");
			}			

		}catch(Exception ex) {ex.printStackTrace();}

		if (!optionValid) {
			status = Status.BAD_REQUEST;
			msg = new Message("Option missing", "Please provide Option: '" + LogOption.FILE_FIXED_SIZE + "' (for 1meg file size before rolling)   OR  " + LogOption.FILE_UNLIMITED + " unlimited ");
			return Response.status(status).entity(msg).build();
		}

		try {
			//verify if it exist 1st.. if it does , copy it.
			boolean fileCreated = false;

			if (logFile.exists()) {
				logFile  = 	copyFile(logFile);
				fileCreated = true;
			}else {
				fileCreated = 	logFile.createNewFile();
			}

			if (fileCreated) {
				msg = new Message("FileName", logFile.getName());
				return Response.ok(msg).build();			 
			}else {
				logger.error("Error creating file: " + logFile);
				msg = new Message("File Creation error", "Error creating file");
			}
		} catch (IOException e) {
			logger.error("Error creating log file" , e );
		}
		return Response.status(status).entity(msg).build();
	}

	@Path("log")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response log(String debugMessage) {

		//		System.out.println("debugMessage: " + debugMessage);
		Message msg = null;

		Status status = Status.BAD_REQUEST;

		if (debugMessage != null) {
			try {
				LogMessage logMessage = gson.fromJson(debugMessage, LogMessage.class);

				if (logMessage.getLogFileName() != null &&
						logMessage.getLogFileName().trim().length() >0 ) {					


					File logFile = new File(logPath + logMessage.getLogFileName());					

					if (logFile.exists()) {

						//check if it hit the 10 megs .. if yes, then make a backup of the file and start new.
						LogOption opt = LogOption.valueOf(logMessage.getLogFileOption());
						if (opt == LogOption.FILE_FIXED_SIZE || opt == LogOption.FILE_FIXED_SIZE_NO_DATE) {
							rollOver(logFile);
						}

						String logDate = LocalDateTime.now().format(logFileDateMessage);

						StringBuilder sb = new StringBuilder();						
						sb.append(logDate + " ");
						sb.append(logMessage.getLevel().toUpperCase() + " ");
						sb.append(logMessage.getClassName() + " ");
						sb.append(logMessage.getErrorMessage() + "\n");

						if (logMessage.getFullErrorStack() != null && logMessage.getFullErrorStack().trim().length() > 0) {
							sb.append(logMessage.getFullErrorStack() + "\n");
						}

						try {
							Files.write(
									Paths.get(logFile.toURI()), 
									sb.toString().getBytes(), 
									StandardOpenOption.APPEND);

							return Response.ok().build();
						} catch (IOException e) {
							logger.error("Writing to logfile" , e);
						}

					}else {
						msg = new Message("Log file name", "Log File does not exist. please create it first. " + logMessage.getLogFileName());
					}					

				}else {
					msg = new Message("Log file name", "Log file name missing.");
				}				
			}catch (JsonSyntaxException x) {
				logger.error("Json syntax exception" , x);
				msg = new Message("Json Syntax", "Cannot convert String to LogMessage.");	
			} 

		}else {
			msg = new Message("Log missing", "Log message information missing");	
		}



		return Response.status(status).entity(msg).build();
	}


	private File rollOver(File logFileName) {

		long length = logFileName.length();

		double size = length;

		int result = (int)(size / 1024 / 1024);

		if (result == 15) {
		
			logFileName = copyFile(logFileName);
		}

		return logFileName;

	}
	private File copyFile(File logFile) {
		boolean exist = true;
		int idx = 0;
		File newFile = null;
		while(exist) {				
			newFile = new File(logFile.getPath() + "." + idx);
			exist = newFile.exists();
			if(exist) {
				idx++;
			}
//			System.out.println("exist: " + exist + "  filename: " + newFile.getPath());
		}

//		System.out.println("New file to create: " + newFile.getPath());
		//copy currect file into new file
		try {
			Files.copy(logFile.toPath(), newFile.toPath());

			Files.delete(logFile.toPath());

			//create empty base on the original file
			boolean c = logFile.createNewFile();

			if (!c) {
				logger.error("Error creating new file for: " + logFile.getPath());
			}

//			System.out.println("File created: " + c);


		} catch (IOException e) {
			logger.error("Error rooling out new file for: " + logFile.getPath() , e);
		}
		return logFile;
	}

}
