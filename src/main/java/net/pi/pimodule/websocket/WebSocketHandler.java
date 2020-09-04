package net.pi.pimodule.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;





//{
//	  "operation": 1,
//	  "user": {
//	    "username": "alice",
//	    "password": "123456"
//	  }
//	}


// {
//"operation": 1,
//"user": "bob"
//}

public class WebSocketHandler {


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
			jse.printStackTrace();
		}

		if (data.operation == Data.IDENTIFICATION) {
			System.out.println("Add data: " + data);
			addUser(session, data.userName);
		}else if (data.operation == Data.GARAGE_FUNCTION){
			System.out.println("garage");
			//get garage status and send to all websoceets registered.
			for(UserSession u: userSessions) {
				System.out.println("Name: " + u.getUserName());
				u.SendData("hi " + u.getUserName() + " i got the following info: " + data.garageDoorStatus);
			}
		}else if(data.operation == Data.TERMINATE_SESSION) {
			System.out.println("Teminating session : " + session.getId());
			session.close();
		}else {
			session.getBasicRemote().sendText("Error, unknown command, terminating session");
			session.close();
		}

	}

	public void processOnClose(Session session) {
		UserSession us = userSessions.stream().filter(u-> u.getSession().getId().equals(session.getId())).findAny().orElse(null);

		if (us != null) {
			System.out.println("User session size before: " +userSessions.size());
			userSessions.remove(us);
			System.out.println("User session size: " +userSessions.size());
		}
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
