package net.pi.pimodule.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import home.common.data.Service;

public class UserSession {
	
	private static final Logger logger = LogManager.getLogger(UserSession.class);
	
	private Session session;
	private String userName = "" ;
	
	//that the socket will register to a certain service.. if it want to receive any garage notification, then it need to register to it..
	private List<Service> services;	
	
	public UserSession() {}
	public UserSession(Session session, Data data) {
		this.session = session;
		this.userName = data.userName;
		
			addService(data);
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
	public List<Service> getServices() {
		return services;
	}
	public void setServices(List<Service> services) {
		this.services = services;
	}
	public boolean wantNotification(Service service) {
		if (this.services != null) {
			return this.services.stream().anyMatch(s -> s == service);
		}
		return false;
	}
	public void addService(Data data) {
		if (data.service != null && data.service.trim().length() > 0) {
			try {
				this.services = new ArrayList<>();
				this.services.add(Service.valueOf(data.service));
			}catch (Exception ex) {
				logger.info("Cannot add service , it does not exist" , ex);
			}
		}
	}

}
