import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.io.*;

public class FireBaseService {
    FirebaseDatabase db;

    public FireBaseService()  {
        System.out.println("inside firebase");
        try{

            InputStream inputStream=  getClass().getClassLoader().
                   getResourceAsStream("firebase.json");
            System.out.println("inside inputstream===>"+inputStream.available());

            FirebaseOptions options = null;
            try {
                options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(inputStream))
                        .setDatabaseUrl("https://lunchbox-13b98.firebaseio.com/")
                        .build();
                System.out.println("options=>>>"+options.getDatabaseUrl());
            } catch (Exception e) {
                e.printStackTrace();
            }

            try{
                db = FirebaseDatabase.getInstance();
            } catch (IllegalStateException e)
            {
                FirebaseApp.initializeApp(options);
                db = FirebaseDatabase.getInstance();
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FirebaseDatabase getDb() {
        return db;
    }

}