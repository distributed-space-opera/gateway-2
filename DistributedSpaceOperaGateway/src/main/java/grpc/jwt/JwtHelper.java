package grpc.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.Map;

public class JwtHelper {
    Algorithm algorithm;
    String issuer = "DistributedSpaceOpera";
    String secret = "secret";

    public JwtHelper() {
        algorithm = Algorithm.HMAC256(secret);
    }

    public String getToken(String clientIp, String type) {
        try {
            LocalDateTime localDateTime = LocalDateTime.now().plusHours(1);

            Map<String, Object> payloadClaims = new HashMap<>();
            payloadClaims.put("ip", clientIp);
            payloadClaims.put("requester", type);

            JWTCreator.Builder tokenBuilder = JWT.create();

            tokenBuilder.withPayload(payloadClaims);
            tokenBuilder.withIssuer(issuer);
            tokenBuilder.withExpiresAt(Date.from(localDateTime.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));

            return tokenBuilder.sign(algorithm);
        } catch (JWTCreationException e) {
            System.out.println("Error in creating JWT Token");
            return "";
        }
    }

    public boolean verifyToken(String clientIp, String clientToken) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT jwt = verifier.verify(clientToken);

            if (!clientIp.equals(jwt.getClaim("ip"))) {
                return false;
            }

            return true;
        } catch (JWTVerificationException e) {
            System.out.println(e);
            return false;
        }
    }
}
