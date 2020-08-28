import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FireBaseService {


    FirebaseDatabase db;

    public FireBaseService()  {
        File file = new File(
                getClass().getClassLoader().getResource("firebase1.json").getFile());

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(fis))
                    .setDatabaseUrl("https://lunchbox-13b98.firebaseio.com/")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            FirebaseApp.getInstance();
            db = FirebaseDatabase.getInstance();
        }
        catch (IllegalStateException e)
        {
            FirebaseApp.initializeApp(options);
            db = FirebaseDatabase.getInstance();
        }

    }

    public FirebaseDatabase getDb() {
        return db;
    }

}