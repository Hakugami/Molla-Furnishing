package firebase;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.*;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class FirebaseManager {
    private FirebaseApp app;
    private Storage storage;
    private GoogleAuthorizationCodeFlow flow;

    public FirebaseManager() throws IOException {
        //from resource file
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(Objects.requireNonNull(getClass().getResourceAsStream("/molla-49da9-firebase-adminsdk-hpg9v-95470780dc.json"))))
                .setDatabaseUrl("https://molla-49da9-default-rtdb.europe-west1.firebasedatabase.app/")
                .setStorageBucket("molla-49da9.appspot.com")
                .build();
        app = FirebaseApp.initializeApp(options);
        storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(Objects.requireNonNull(getClass().getResourceAsStream("/molla-49da9-firebase-adminsdk-hpg9v-95470780dc.json")))).build().getService();
    }

    public String uploadFileToStorage(byte[] fileBytes, String filename) throws IOException {
        Bucket bucket = StorageClient.getInstance(app).bucket();
        BlobId blobId = BlobId.of(bucket.getName(), filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
        Blob blob = storage.create(blobInfo, fileBytes);
        URL url = blob.signUrl(100, TimeUnit.DAYS,
                Storage.SignUrlOption.signWith((ServiceAccountSigner) GoogleCredentials.fromStream(Objects.requireNonNull(getClass().getResourceAsStream("/molla-49da9-firebase-adminsdk-hpg9v-95470780dc.json")))));
        System.out.println(url.getHost() + url.getFile());
        return url.getProtocol() + "://" + url.getHost() + url.getFile();

    }

    public void authenticateUser(String email, String password) {
        DatabaseReference ref = FirebaseDatabase.getInstance(app).getReference("users");
        ref.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.child("password").getValue().equals(password)) {
                            System.out.println("User authenticated");
                        } else {
                            System.out.println("Invalid password");
                        }
                    }
                } else {
                    System.out.println("User not found");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }


    public static void main(String[] args) {
//        try {
//            FirebaseManager firebaseManager = new FirebaseManager();
//            FileInputStream fileInputStream = new FileInputStream("D:\\Screenshot_3.png");
//            byte[] bytes = fileInputStream.readAllBytes();
//            String url = firebaseManager.uploadFileToStorage(bytes, "Screenshot_3.png");
//            System.out.println(url);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}