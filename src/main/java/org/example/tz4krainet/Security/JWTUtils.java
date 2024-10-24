package org.example.tz4krainet.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.tz4krainet.Models.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtils {

    @Value("${jwt.key}")
    private String key;

    // Генерация токена
    public String generateToken(Users users) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", users.getUser_id());
        claims.put("role", users.getUserRole());


        return JWT.create()
                .withSubject("Users details")
                .withClaim("id", users.getUser_id())
                .withClaim("role", users.getUserRole())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000 * 60)) // срок действия токена
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(key));
    }
    public Map<String, Claim> validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key))
                .withSubject("Users details")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> jwt_info=new HashMap<>();
        jwt_info.put("role",jwt.getClaim("role"));
        jwt_info.put("id",jwt.getClaim("id"));
        return jwt_info;
    }

}