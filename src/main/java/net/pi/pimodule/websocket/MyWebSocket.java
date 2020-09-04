package net.pi.pimodule.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/events/")
public class MyWebSocket {

	//	private static PushTimeService pst;

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("onOpen::" + session.getId());     
		session.setMaxIdleTimeout(86400000);// the seession last 24 hours or when the terminate connection is recieveds
	}
	@OnClose
	public void onClose(Session session) {
		System.out.println("onClose::" +  session.getId());		
		WebSocketHandler.getInstance().processOnClose(session);
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("onMessage::From=" + session.getId() + " Message=" + message);

		try {
			WebSocketHandler.getInstance().processMessage(session, message);
		}catch(IOException e) {
			e.printStackTrace();
		}
		//		try {
		//			session.getBasicRemote().sendText("Hello Client " + session.getId() + "!");
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}
	}

	@OnError
	public void onError(Throwable t) {
		System.out.println("onError::" + t.getMessage());
	}
}