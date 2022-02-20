package com.bilgeadam.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {

    private final String secret = "123";
    private final String issuer = "bilgeadam.com";

    public Optional<String> createToken(String profileId){
        String token;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            /**
             * Claim -> any object like key:value
             * Issuer -> Owner
             * IssuedAt -> Creation time
             * sign -> secret key for sign
             */
            token = JWT.create()
                    .withAudience()
                    .withClaim("profileId", profileId)
                    .withIssuer(issuer)
                    .withExpiresAt(new Date(System.currentTimeMillis()+ 1000*60*60))
                    .withIssuedAt(new Date())
                    .sign(algorithm);

            return Optional.of(token);

        } catch (Exception e){
            return Optional.empty();
        }
    }

    public boolean validateTokenOne(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            if (decodedJWT==null)
                return false;
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public Optional<String> getProfileId(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();

            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            if (decodedJWT == null)
                return Optional.empty();

            String profileId = decodedJWT.getClaim("profileId").asString();
            return Optional.of(profileId);

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
