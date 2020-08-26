



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


@ServerEndpoint(value = "/LunchBox")

public class LunchBoxEndpoint {
    ArrayList<Session> sessionArrayList = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();
    Connection con = DatabaseConnection.getconnection();

    @OnOpen

    public void onOpen(Session session) {

    }



    @OnMessage

    public String onMessage(String message, Session session) throws IOException, SQLException {
        JsonNode actualObj = mapper.readTree(message);
        DatabaseOperations databaseOperations = new DatabaseOperations();

        if(actualObj.get("action").asText().contains("android")){
          sessionArrayList.add(session);
     }else if(actualObj.get("action").asText().contains("send to")){
            sessionArrayList.get(0).getAsyncRemote().sendText(message);
        }else if(actualObj.get("action").asText().contains("fileUpload")){
            databaseOperations.insertIntoProducts(con, actualObj);
        return "success";
        }
        return null;

    }



    @OnClose

    public void onClose(Session session, CloseReason closeReason) {


    }

}