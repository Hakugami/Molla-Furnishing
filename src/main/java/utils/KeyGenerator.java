package utils;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.lang.JoseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;

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

    private PrivateKey getPrivateKey() {
        try (PEMParser pemParser = new PEMParser(new FileReader("src/main/webapp/keys" + "/privatekey.pem"))) {
            Object object = pemParser.readObject();
            PEMKeyPair pemKeyPair = new PEMKeyPair(null, (PrivateKeyInfo) object);
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
            PrivateKey privateKey = converter.getPrivateKey(pemKeyPair.getPrivateKeyInfo());
            return privateKey;
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    private PublicKey getPublicKey() {
        try (PEMParser pemParser = new PEMParser(new FileReader("src/main/webapp/keys" + "/publickey.pem"))) {
            Object object = pemParser.readObject();
            PEMKeyPair pemKeyPair = new PEMKeyPair((SubjectPublicKeyInfo) object, null);
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
            PublicKey publicKey = converter.getPublicKey(pemKeyPair.getPublicKeyInfo());
            return publicKey;
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public void createPrivateKeyFile(RsaJsonWebKey rsaJsonWebKey) throws JoseException, IOException {
        File file = new File("src/main/webapp/keys" + "/privatekey.pem");
        file.getParentFile().mkdirs();
        FileWriter fileWriter = new FileWriter(file);
        PemWriter pemWriter = new PemWriter(fileWriter);
        pemWriter.writeObject(new PemObject("PRIVATE KEY", rsaJsonWebKey.getPrivateKey().getEncoded()));
        pemWriter.flush();
        pemWriter.close();
    }

    public void createPublicKeyFile(RsaJsonWebKey rsaJsonWebKey) throws JoseException, IOException {
        File file = new File("src/main/webapp/keys" + "/publickey.pem");
        file.getParentFile().mkdirs();
        FileWriter fileWriter = new FileWriter(file);
        PemWriter pemWriter = new PemWriter(fileWriter);
        pemWriter.writeObject(new PemObject("PUBLIC KEY", rsaJsonWebKey.getPublicKey().getEncoded()));
        pemWriter.flush();
        pemWriter.close();
    }
}