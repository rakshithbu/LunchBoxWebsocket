import Model.Category;
import Model.MenuItem;
import Model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.firebase.database.*;
import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DatabaseOperations {
    ObjectMapper mapper = new ObjectMapper();

    public void getAllCategories(Session session,FirebaseDatabase db) {

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


    public void getAllCategoriesMenuList(Session session,FirebaseDatabase db){

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
                   // menuItem.setCategoryName(getNameBasedOnCategoryId(menuItem.getCatId()));
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
        menuItem.setInStock(true);
        presentersReference.child(presenterId).setValue(menuItem, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {

                } else {
                }
            }
        });

    }

    public void updateCategory(FirebaseDatabase db,Category category){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("categories").child(category.getCatId());
        Map<String, Object> updates = new HashMap<>();

        if(category.getCatName()!=null &&  !category.getCatName().isEmpty()){
            ref.child("catName").setValueAsync(category.getCatName());
            //updates.put("catName", category.getCatName());
        }

        if(category.getCatImage()!=null &&  !category.getCatImage().isEmpty()){
            ref.child("catImage").setValueAsync(category.getCatImage());
            //updates.put("catImage", category.getCatImage());
        }
    }

    public void updateMenuItemInStock(FirebaseDatabase db,String menuId,boolean isInStock,Map<String,Session> sessionMap){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("itemMenus").child(menuId);
        Map<String, Object> updates = new HashMap<>();

        if(menuId!=null &&  !menuId.isEmpty()){
            ref.child("inStock").setValueAsync(isInStock);
            for (String key: sessionMap.keySet()) {
                ObjectNode jsonNode = mapper.createObjectNode();
                jsonNode.put("action","stockNotification");

                if(isInStock){
                    jsonNode.put("data","item is instock");
                }else{
                    jsonNode.put("data","item out of stock");
                }

                try{
                    sessionMap.get(key).getAsyncRemote().sendText(mapper.writeValueAsString(jsonNode));
                }catch (Exception e){
                    e.printStackTrace();
                }

                System.out.println("value : " + sessionMap.get(key));
            }
        }
    }

    public void getAllCActiveOrders(Session session,FirebaseDatabase db){

        DatabaseReference ref = db.getReference("/orders");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Order order = postSnapshot.getValue(Order.class);
                    try {
                        if(order.getStatus().equalsIgnoreCase("IN PROGRESS")){
                            ObjectNode jsonNode = mapper.createObjectNode();
                            jsonNode.put("action","getActiveOrders");
                            jsonNode.put("data",mapper.writeValueAsString(order));
                            session.getAsyncRemote().sendText(mapper.writeValueAsString(jsonNode));
                        }
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

    public void getAllOrders(Session session,FirebaseDatabase db){

        DatabaseReference ref = db.getReference("/orders");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Order order = postSnapshot.getValue(Order.class);
                    try {
                        //if(!order.getStatus().equalsIgnoreCase("IN PROGRESS")){
                            ObjectNode jsonNode = mapper.createObjectNode();
                            jsonNode.put("action","getAllOrders");
                            jsonNode.put("data",mapper.writeValueAsString(order));
                            session.getAsyncRemote().sendText(mapper.writeValueAsString(jsonNode));
                      //  }
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

    public void updateOrder(FirebaseDatabase db,String orderId,String status){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("orders").child(orderId);
        if(orderId!=null &&  !orderId.isEmpty() ){
            ref.child("status").setValueAsync(status);
        }
    }

//    public String getNameBasedOnCategoryId(String categoryId){
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("categories").child(categoryId);
//
//        System.out.println(ref.child("catName").getKey());
//
//
//        Category category2 = new Category();
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//
//                for (DataSnapshot user: snapshot.getChildren()) {
//                    Category category = user.getValue(Category.class);
//                    //this is all you need to get a specific user by Uid
//                    if (category.getCatId().equals(categoryId)){
//                        category2.setCatName(category.getCatName());
//
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        return category2.getCatName();
//    }


}
