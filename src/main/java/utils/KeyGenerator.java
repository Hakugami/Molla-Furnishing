package utils;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
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

    public KeyPair generateKeyPairForUser(String userId) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchProviderException {
        KeyPair keyPair = null;

        Path privateKeyPath = Paths.get(userId + "_private.pem");
        Path publicKeyPath = Paths.get(userId + "_public.pem");
        if (Files.exists(privateKeyPath) && Files.exists(publicKeyPath)) {
            PEMParser pemParser = new PEMParser(new FileReader(privateKeyPath.toString()));
            Object object = pemParser.readObject();
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
            PrivateKey privateKey;
            PublicKey publicKey;
            if (object instanceof PEMKeyPair) {
                PEMKeyPair pemKeyPair = (PEMKeyPair) object;
                keyPair = converter.getKeyPair(pemKeyPair);
                publicKey = keyPair.getPublic();
            } else if (object instanceof PrivateKeyInfo) {
                privateKey = converter.getPrivateKey((PrivateKeyInfo) object);
                // Read the public key from the file
                pemParser = new PEMParser(new FileReader(publicKeyPath.toString()));
                object = pemParser.readObject();
                if (object instanceof SubjectPublicKeyInfo) {
                    publicKey = converter.getPublicKey((SubjectPublicKeyInfo) object);
                } else {
                    throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
                }
                keyPair = new KeyPair(publicKey, privateKey);
            } else {
                throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
            }
        } else {
            System.out.println("Generating new keys");
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