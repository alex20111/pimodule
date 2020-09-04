package net.pi.pimodule.websocket;

import java.io.IOException;

import javax.websocket.Session;

public class UserSession {
	
	private Session session;
	private String userName = "" ;

	
	
	public UserSession() {}
	public UserSession(Session session, String userName) {
		this.session = session;
		this.userName = userName;
	}
	
	
	public void SendData(String data) throws IOException {
		session.getBasicRemote().sendText(data);
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
