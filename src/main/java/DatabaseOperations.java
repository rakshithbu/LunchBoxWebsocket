import Model.Category;
import Model.MenuItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.firebase.database.*;

import javax.websocket.Session;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class DatabaseOperations {
    ObjectMapper mapper = new ObjectMapper();

    public void getAllCategories(Session session,FirebaseDatabase db) throws JsonProcessingException {

        DatabaseReference ref = db.getReference("/categories");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Category category = postSnapshot.getValue(Category.class);
                    try {
                        ObjectNode jsonNode = mapper.createObjectNode();
                        jsonNode.put("action","getAllCategories");
                        jsonNode.put("data",mapper.writeValueAsString(category));
                        session.getAsyncRemote().sendText(mapper.writeValueAsString(jsonNode));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }


    public void getAllCategoriesMenuList(Session session,FirebaseDatabase db) throws JsonProcessingException {

        DatabaseReference ref = db.getReference("/categories");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Category category = postSnapshot.getValue(Category.class);
                    try {
                        ObjectNode jsonNode = mapper.createObjectNode();
                        jsonNode.put("action","getAllCategoriesMenuList");
                        jsonNode.put("data",mapper.writeValueAsString(category));
                        session.getAsyncRemote().sendText(mapper.writeValueAsString(jsonNode));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void insertIntoCategories(FirebaseDatabase db,Category category){
        DatabaseReference presentersReference = FirebaseDatabase.getInstance().getReference("categories");
        final String presenterId = UUID.randomUUID().toString();
        category.setCatId(presenterId);
        presentersReference.child(presenterId).setValue(category, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {

                } else {
                }
            }
        });
    }

    public void getAllMenuItems(Session session,FirebaseDatabase db) throws JsonProcessingException {

        DatabaseReference ref = db.getReference("/itemMenus");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    MenuItem menuItem = postSnapshot.getValue(MenuItem.class);
                    try {
                        ObjectNode jsonNode = mapper.createObjectNode();
                        jsonNode.put("action","getAllMenuItems");
                        jsonNode.put("data",mapper.writeValueAsString(menuItem));
                        session.getAsyncRemote().sendText(mapper.writeValueAsString(jsonNode));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void insertIntoMenuItem(FirebaseDatabase db,MenuItem menuItem){

        DatabaseReference presentersReference = FirebaseDatabase.getInstance().getReference("itemMenus");
        final String presenterId = UUID.randomUUID().toString();
        menuItem.setItemId(presenterId);
        presentersReference.child(presenterId).setValue(menuItem, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {

                } else {
                }
            }
        });

    }


}
