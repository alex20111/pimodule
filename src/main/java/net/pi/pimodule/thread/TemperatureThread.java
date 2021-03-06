package net.pi.pimodule.thread;

import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import home.common.data.Temperature;
import net.pi.pimodule.User;
//import net.pi.pimodule.common.TemperatureHandler;
import net.pi.pimodule.db.TempSql;

public class TemperatureThread implements Runnable{

	private static final Logger logger = LogManager.getLogger(TemperatureThread.class);

	private TempSql sql;
	private boolean needToLogIn = true;
	private String token = "";

	private WebTarget webTarget;

 /**
  * Clas that will push the temperature data to the server.. 
  */
	public TemperatureThread() {

		sql = new TempSql();
		Client client = ClientBuilder.newClient();
		webTarget = client.target("https://www.boudreault.xyz/bwservice/webapi"); 
	}

	public void run() {
		logger.debug("--> Temperature thread sampling at: " + new Date());

		try {
//			Temperature t = TemperatureHandler.getInstance().getTemperature(); //tempListener.getTemp();
			Temperature t = sql.getCurrentStoredTemperature();
			
			if (t != null) {
				logger.info("--> Temperature thread  info, sending: " + t);
				if (needToLogIn) {
					token = login(webTarget);
				}

				if (token.length() > 0) {
					sendData(webTarget, token, t);
				}else {
					logger.info("No token recieved" );
				}

			}


		}catch(Throwable tr) {
			logger.error("Error in thread. " , tr);
		}
	}

	private  String login(WebTarget webTarget) {
		//LOGIN
		WebTarget loginTarget  = webTarget.path("auth/login");

		String userName = "weatherBot";
		String passHashed = "0299c438465ea02b89cd201b20932ca8b5c4a0937f971853abbf349763eab3dabf8b7a9b9b3e301fafe05fd484093fc944e53651df253c4cf0d1e7e9605cef13";

		String str = "{ \"userName\":\""+userName+"\",\"password\":\""+passHashed+"\",\"websiteToAccess\":\"HEADLESS\" } ";

		Invocation.Builder invocationBuilder =  loginTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(str, MediaType.APPLICATION_JSON));

		User u = response.readEntity(User.class);

		logger.info("Logged in with user: " + u);
		needToLogIn = false;

		return u.getAuthToken();
	}
	private  void sendData(WebTarget webTarget, String token, Temperature temp) {
		//UPDATE
		WebTarget tempUpdTarget  = webTarget.path("temperature/update");
		 
		Invocation.Builder invBuilderTemp = tempUpdTarget.request(MediaType.APPLICATION_JSON);
		Response responseT = invBuilderTemp.header("Authorization", "Bearer " + token).post(Entity.entity(temp, MediaType.APPLICATION_JSON));

		String resp = responseT.readEntity(String.class);

		if (resp.contains("Invalid user token")) {
			//this means we need to re-login
			needToLogIn = true;
		}
		logger.debug("Response token: " +resp);

	}
	

}
