package net.pi.pimodule.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ServerEndpoint(value="/events/")
public class MyWebSocket {

	private static final Logger logger = LogManager.getLogger(MyWebSocket.class);

	@OnOpen
	public void onOpen(Session session) {
		logger.debug("onOpen:: " + session.getId());        
		session.setMaxIdleTimeout(86400000);// the seession last 24 hours or when the terminate connection is recieveds
	}
	@OnClose
	public void onClose(Session session) {
		logger.debug("onClose:: " +  session.getId());		
		WebSocketHandler.getInstance().processOnClose(session);
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		logger.debug("onMessage:: From=" + session.getId() + " Message=" + message);

		try {
			WebSocketHandler.getInstance().processMessage(session, message);
		}catch(IOException e) {
			logger.error("on message error: " , e);
		}
	
	}

	@OnError
	public void onError(Throwable t) {
		logger.error("onError::" , t);
	}
}