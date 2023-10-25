
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/notification")
public class WebSocketServer {

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("WebSocket connection opened: " + session.getId());
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("Received message: " + message);
		try {
			// Send a notification message to the client
			session.getBasicRemote().sendText("You have a new notification: " + message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("WebSocket connection closed: " + session.getId());
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		System.err.println("WebSocket error: " + throwable.getMessage());
	}
}
