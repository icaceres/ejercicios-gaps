package com.gateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;

public class JwtValidationTest {

    @Test
    public void testTokenValidation() {
        String token = Jwts.builder()
                .setSubject("test-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000L)) // Expira en 1 hora
                .signWith(JwtUtil.getSecretKey(), SignatureAlgorithm.HS512)
                .compact();

        NimbusReactiveJwtDecoder jwtDecoder = NimbusReactiveJwtDecoder
                .withSecretKey(JwtUtil.getSecretKey())
                .macAlgorithm(MacAlgorithm.HS512) // Especifica HS512
                .build();

        Mono<Jwt> jwt = jwtDecoder.decode(token);

        StepVerifier.create(jwt)
                .assertNext(decodedJwt -> {
                    System.out.println("Token válido. Subject: " + decodedJwt.getSubject());
                })
                .verifyComplete();
    }
}
