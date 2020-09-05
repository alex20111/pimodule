package net.pi.pimodule.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;



// {
//"operation": 1,
//"user": "bob"
//}

public class WebSocketHandler {
	private static final Logger logger = LogManager.getLogger(WebSocketHandler.class);
	
	private static WebSocketHandler instance;

	private List<UserSession> userSessions = new ArrayList<UserSession>();
	private Gson gson = new Gson();

	public static WebSocketHandler getInstance() {
		if (instance == null) {
			instance = new WebSocketHandler();
		}
		return instance;
	}

	public void processMessage(Session session, String message) throws IOException {

		Data data = new Data();
		try {
			data =  gson.fromJson(message, Data.class);
		}catch (JsonSyntaxException jse) {
			logger.error("Error in JSON: ", jse);
		}

		if (data.operation == Data.IDENTIFICATION) {
			logger.debug("Add Identifier: " + data);
			addUser(session, data.userName);
		}else if (data.operation == Data.GARAGE_FUNCTION){
			logger.debug("garage info recieved: "+  data);
			//get garage status and send to all websoceets registered.
			for(UserSession u: userSessions) {
				logger.debug("Sending to: " + u.getUserName());
				u.SendData( "{ \"garageStatus\" : " +  data.garageDoorStatus + "}");
			}
		}else if(data.operation == Data.TERMINATE_SESSION) {
			logger.debug("Teminating session : " + session.getId());
			session.close();
		}else if (data.operation == Data.HEART_BEAT) {
			logger.info("Heart beat from: " + session.getId());
		}
		else {
			logger.info("Unknown command: " + session.getId());
			session.getBasicRemote().sendText("Error, unknown command, terminating session");
			session.close();
		}

	}

	public void processOnClose(Session session) {
		UserSession us = userSessions.stream().filter(u-> u.getSession().getId().equals(session.getId())).findAny().orElse(null);

		if (us != null) {			
			userSessions.remove(us);
		}
		logger.debug("processing on close. User pool: " + userSessions.size() );
	}


	private void addUser(Session session, String userName) throws IOException {

		//check if user exist, if exist.. remove and replace session

		UserSession us = userSessions.stream().filter(u -> u.getUserName().equals(userName)).findAny().orElse(null);

		if (us != null) {
			us.setSession(session);
			us.SendData("User exist.. replacing" );
		}else {
			System.out.println("Adding new user: " + userName);
			UserSession u = new UserSession(session, userName);

			userSessions.add(u);
		}


	}

}
