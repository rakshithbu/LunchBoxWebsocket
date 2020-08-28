





import Model.Category;
import Model.MenuItem;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@ServerEndpoint(value = "/LunchBox")

public class LunchBoxEndpoint {
   static Map<String,Session> userIdSession = new HashMap<>();
   static ArrayList<Session>  webSessionList = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session) {

        System.out.println("connection opened");
    }

    @OnMessage
    public String onMessage(String message, Session session) throws IOException {

        System.out.println(message);

        try{
            FireBaseService fireBaseService = new FireBaseService();
            DatabaseOperations databaseOperations = new DatabaseOperations();

            JsonNode actualObj = mapper.readTree(message);


            if(actualObj.get("action").asText().equalsIgnoreCase("androidDeviceConnect")){
               String userId = actualObj.get("userId").asText();
                ObjectNode n = mapper.createObjectNode();
                n.put("action","stockNotification");
               session.getAsyncRemote().sendText(mapper.writeValueAsString(n));
                userIdSession.put(userId,session);
            }else if(actualObj.get("action").asText().equalsIgnoreCase("websession")){
                webSessionList.add(session);
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
            }else if(actualObj.get("action").asText().equalsIgnoreCase("updateCategory")) {
                Category category = mapper.readValue(actualObj.get("data").asText(), Category.class);
                databaseOperations.updateCategory(fireBaseService.getDb(),category);
            }else if(actualObj.get("action").asText().equalsIgnoreCase("isInStock")) {
                boolean isInStock = actualObj.get("isInStock").asBoolean();
                String menuId = actualObj.get("itemId").asText();
                databaseOperations.updateMenuItemInStock(fireBaseService.getDb(),menuId,isInStock,userIdSession);
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