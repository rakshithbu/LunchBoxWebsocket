





import Model.Category;
import Model.MenuItem;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;

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

    @OnOpen
    public void onOpen(Session session) {

        System.out.println("connection opened");
    }

    @OnMessage
    public String onMessage(String message, Session session) throws IOException {

        System.out.println("inside on message");

        try{
            FireBaseService fireBaseService = new FireBaseService();
            DatabaseOperations databaseOperations = new DatabaseOperations();

            JsonNode actualObj = mapper.readTree(message);


            if(actualObj.get("action").asText().contains("android")){
                sessionArrayList.add(session);
            }else if(actualObj.get("action").asText().equalsIgnoreCase("send to")){
                sessionArrayList.get(0).getAsyncRemote().sendText(message);
            }else if(actualObj.get("action").asText().equalsIgnoreCase("fileUpload")){
                //   databaseOperations.insertIntoProducts(con, actualObj);
                return "success";
            }else if(actualObj.get("action").asText().equalsIgnoreCase("getAllCategories")){
                databaseOperations.getAllCategories(session,fireBaseService.getDb());
            }else if(actualObj.get("action").asText().equalsIgnoreCase("saveCategories")){
              Category category = mapper.readValue(actualObj.get("data").asText(),Category.class);
              databaseOperations.insertIntoCategories(fireBaseService.getDb(),category);
            }else if(actualObj.get("action").asText().equalsIgnoreCase("getAllMenuItems")){
                databaseOperations.getAllMenuItems(session,fireBaseService.getDb());
            }else if(actualObj.get("action").asText().equalsIgnoreCase("getAllCategoriesMenuList")){
                databaseOperations.getAllCategoriesMenuList(session,fireBaseService.getDb());
            }else if(actualObj.get("action").asText().equalsIgnoreCase("saveItems")){
               MenuItem menuItem = mapper.readValue(actualObj.get("data").asText(), MenuItem.class);
                databaseOperations.insertIntoMenuItem(fireBaseService.getDb(),menuItem);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @OnClose

    public void onClose(Session session, CloseReason closeReason) {


    }

}