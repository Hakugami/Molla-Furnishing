package services;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import utils.KeyGenerator;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class JWTService {

    private static volatile JWTService instance;
    private RsaJsonWebKey rsaJsonWebKey;
    private Logger logger = Logger.getLogger(getClass().getName());

    private JWTService() {
        try {
            rsaJsonWebKey = generateRSAJsonWebKey();
        } catch (JoseException | IOException e) {
            logger.severe("Error generating RSAJsonWebKey: " + e.getMessage());
        }

    }

    public static JWTService getInstance() {
        if (instance == null) {
            synchronized (JWTService.class) {
                if (instance == null) {
                    instance = new JWTService();
                }
            }
        }
        return instance;
    }

    public void init() {
    }

    public JsonWebSignature getNewSignedToken() {
        JsonWebSignature jws = new JsonWebSignature();

        jws.setKey(rsaJsonWebKey.getPrivateKey());
        jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_PSS_USING_SHA512);

        return jws;
    }

    public JwtClaims validateToken(String token, String audience) throws InvalidJwtException, UnknownHostException {
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds(30)
                .setExpectedIssuer("http://" + InetAddress.getLocalHost().getHostAddress() + ":4545/molla/api")
                .setRequireSubject()
                .setExpectedAudience(audience)
                .setEnableRequireIntegrity()
                .setVerificationKey(rsaJsonWebKey.getKey())
                .setJwsAlgorithmConstraints(
                        AlgorithmConstraints.ConstraintType.PERMIT, AlgorithmIdentifiers.RSA_PSS_USING_SHA512)
                .build();
        return jwtConsumer.processToClaims(token);
    }

    private RsaJsonWebKey generateRSAJsonWebKey() throws JoseException, IOException {
        RsaJsonWebKey rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
        rsaJsonWebKey.setKeyId("RSAJSONWEBKEY");
        KeyGenerator.getInstance().createPrivateKeyFile(rsaJsonWebKey);
        KeyGenerator.getInstance().createPublicKeyFile(rsaJsonWebKey);
        return rsaJsonWebKey;
    }
}