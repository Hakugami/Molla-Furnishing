package services;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;

public class HashService {
    private final String hashAlgorithm;
    private static volatile HashService instance = null;

    private HashService() {
        try {
            // Load the properties file
            InputStream propertiesStream = getClass().getClassLoader().getResourceAsStream("hashing.properties");
            Properties prop = new Properties();
            prop.load(propertiesStream);

            // Retrieve the hash algorithm from the properties file
            this.hashAlgorithm = prop.getProperty("hashAlgorithm");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashService getInstance() {
        if (instance == null) {
            synchronized (HashService.class) {
                if (instance == null) {
                    instance = new HashService();
                }
            }
        }
        return instance;
    }

    public String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public String hashPasswordWithSalt(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
            md.update(salt.getBytes());
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}