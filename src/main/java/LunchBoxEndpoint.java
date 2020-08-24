



import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint(value = "/LunchBox")

public class LunchBoxEndpoint {

    @OnOpen

    public void onOpen(Session session) {

    }



    @OnMessage

    public String onMessage(String message, Session session) {

        System.out.println(message);
        return message;

    }



    @OnClose

    public void onClose(Session session, CloseReason closeReason) {


    }

}