package firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FirebaseManager {
    private DatabaseReference ref;

    public FirebaseManager() throws IOException {
        //from resource file
        InputStream serviceAccount = getClass().getResourceAsStream("molla-49da9-firebase-adminsdk-hpg9v-95470780dc.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://DATABASE_NAME.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);
        ref = FirebaseDatabase.getInstance()
                .getReference("restricted_access/secret_document");
        addValueEventListener();
    }

    private void addValueEventListener() {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                System.out.println(document);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Failed to read value."+ error.toException());
            }
        });
    }
}