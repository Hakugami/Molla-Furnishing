package utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.util.io.pem.PemObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class KeyGenerator {

    private static volatile KeyGenerator instance;

    private KeyGenerator() {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static KeyGenerator getInstance() {
        if (instance == null) {
            synchronized (KeyGenerator.class) {
                if (instance == null) {
                    instance = new KeyGenerator();
                }
            }
        }
        return instance;
    }

    public KeyPair generateKeyPairForUser(String userId) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, InvalidKeySpecException {
        KeyPair keyPair;

        Path privateKeyPath = Paths.get(userId + "_private.pem");
        Path publicKeyPath = Paths.get(userId + "_public.pem");
        if (Files.exists(privateKeyPath) && Files.exists(publicKeyPath)) {
            // Read the keys from the files
            PEMParser pemParser = new PEMParser(new FileReader(privateKeyPath.toString()));
            PEMKeyPair pemKeyPair = (PEMKeyPair) pemParser.readObject();
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
            KeyPair privateKeyPair = converter.getKeyPair(pemKeyPair);

            pemParser = new PEMParser(new FileReader(publicKeyPath.toString()));
            pemKeyPair = (PEMKeyPair) pemParser.readObject();
            KeyPair publicKeyPair = converter.getKeyPair(pemKeyPair);

            keyPair = new KeyPair(publicKeyPair.getPublic(), privateKeyPair.getPrivate());
        } else {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();

            // Save the private key to a PEM file
            try (JcaPEMWriter pemWriter = new JcaPEMWriter(new FileWriter(privateKeyPath.toString()))) {
                pemWriter.writeObject(new PemObject("PRIVATE KEY", keyPair.getPrivate().getEncoded()));
            }

            // Save the public key to a PEM file
            try (JcaPEMWriter pemWriter = new JcaPEMWriter(new FileWriter(publicKeyPath.toString()))) {
                pemWriter.writeObject(new PemObject("PUBLIC KEY", keyPair.getPublic().getEncoded()));
            }
        }

        return keyPair;
    }
}