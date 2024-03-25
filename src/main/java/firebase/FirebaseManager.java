package firebase;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class FirebaseManager {
    private static FirebaseManager instance;
    private final FirebaseApp app;
    private final Storage storage;
    private GoogleAuthorizationCodeFlow flow;

    private FirebaseManager() throws IOException {
        //from resource file
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(Objects.requireNonNull(getClass().getResourceAsStream("/molla-49da9-firebase-adminsdk-hpg9v-95470780dc.json"))))
                .setDatabaseUrl("https://molla-49da9-default-rtdb.europe-west1.firebasedatabase.app/")
                .setStorageBucket("molla-49da9.appspot.com")
                .build();
        app = FirebaseApp.initializeApp(options);
        storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(Objects.requireNonNull(getClass().getResourceAsStream("/molla-49da9-firebase-adminsdk-hpg9v-95470780dc.json")))).build().getService();
    }

    public static FirebaseManager getInstance() throws IOException {
        if (instance == null) {
            instance = new FirebaseManager();
        }
        return instance;
    }

    public String uploadFileToStorage(byte[] fileBytes, String filename, String filetype) throws IOException {
        Bucket bucket = StorageClient.getInstance(app).bucket();
        BlobId blobId = BlobId.of(bucket.getName(), filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(filetype).build();
        Blob blob = storage.create(blobInfo, fileBytes);
        URL url = blob.signUrl(100, TimeUnit.DAYS,
                Storage.SignUrlOption.signWith((ServiceAccountSigner) GoogleCredentials.fromStream(Objects.requireNonNull(getClass().getResourceAsStream("/molla-49da9-firebase-adminsdk-hpg9v-95470780dc.json")))));
        System.out.println(url.getHost() + url.getFile());
        return url.getProtocol() + "://" + url.getHost() + url.getFile();
    }
}