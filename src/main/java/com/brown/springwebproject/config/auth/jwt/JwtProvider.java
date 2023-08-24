package com.brown.springwebproject.config.auth.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;


public class JwtProvider {
    private final String secretKey;

    public JwtProvider(String secretKey) {
        this.secretKey = secretKey;
    }


    public JWTClaimsSet getClaimFromToken(String token) {
        try {
            String tokenContent = token.split(" ")[1];
            JWT parsedToken = JWTParser.parse(tokenContent);
            JWSObject.parse(tokenContent);

            JWTClaimsSet claims = parsedToken.getJWTClaimsSet();
            return claims;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public String generateAccessToken(UserJwtTokenDto userJwtTokenDto) throws Exception {
        Duration expirationTime = Duration.ofHours(6);
        return generateToken(userJwtTokenDto.getClaim(), expirationTime);
    }

    public String generateRefreshToken(UserJwtTokenDto userJwtTokenDto) throws Exception {
        Duration expirationTime = Duration.ofHours(24);
        return generateToken(userJwtTokenDto.getClaim(), null);
    }

    private String generateToken(Map<String, Object> claims, Duration expirationTime) throws Exception {
        JWSSigner signer = new MACSigner(secretKey);

        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();

        for (String key : claims.keySet()) {
            builder.claim(key, claims.get(key));
        }

        JWTClaimsSet claimsSet = builder
                .expirationTime(expirationTime == null ? null : Date.from(Instant.now().plus(expirationTime)))
                .issueTime(new Date())
                .build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT)
                .build(), claimsSet);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }
}