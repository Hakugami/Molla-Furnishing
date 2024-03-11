package services;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import utils.KeyGenerator;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Map;

public class JWTService {

    private static volatile JWTService instance;

    private JWTService() throws Exception {
    }

    public static JWTService getInstance() throws Exception {
        if (instance == null) {
            synchronized (JWTService.class) {
                if (instance == null) {
                    instance = new JWTService();
                }
            }
        }
        return instance;
    }

    public String createJWT(Map<String, Object> claims, String userId) throws JOSEException, Exception {
        KeyPair keyPair = KeyGenerator.getInstance().generateKeyPairForUser(userId);
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        JWSSigner signer = new RSASSASigner(privateKey);

        JWTClaimsSet.Builder claimSetBuilder = new JWTClaimsSet.Builder();
        for (Map.Entry<String, Object> claim : claims.entrySet()) {
            claimSetBuilder.claim(claim.getKey(), claim.getValue());
        }

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID("RSAJSONWEBKEY").build(),
                claimSetBuilder.build());

        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public boolean validateJWT(String token, String userId) throws JOSEException, ParseException, Exception {
    KeyPair keyPair = KeyGenerator.getInstance().generateKeyPairForUser(userId);
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

    // URL decode the token
    String decodedToken = URLDecoder.decode(token, StandardCharsets.UTF_8.toString());

    SignedJWT signedJWT = SignedJWT.parse(decodedToken);

    JWSVerifier verifier = new RSASSAVerifier(publicKey);

    return signedJWT.verify(verifier);
}
}