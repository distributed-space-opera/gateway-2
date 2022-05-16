package grpc.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;
import java.util.Map;

public class JwtHelper {
    Algorithm algorithm;
    String issuer = "DistributedSpaceOpera";

    public JwtHelper() {
        algorithm = Algorithm.none();
    }

    public String getToken() {
        try {
            Map<String, Object> payloadClaims = new HashMap<>();
            JWTCreator.Builder tokenBuilder = JWT.create();

            tokenBuilder.withPayload(payloadClaims);
            tokenBuilder.withIssuer(issuer);
//            tokenBuilder.withExpiresAt()

            return tokenBuilder.sign(algorithm);
        } catch (JWTCreationException e) {
            System.out.println("Error in creating JWT Token");
            return "";
        }
    }

    public boolean verifyToken(String clientToken) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT jwt = verifier.verify(clientToken);

            return true;
        } catch (JWTVerificationException e) {
            System.out.println(e);
            return false;
        }
    }
}
