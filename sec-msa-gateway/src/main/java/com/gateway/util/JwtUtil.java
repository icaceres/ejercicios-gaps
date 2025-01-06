package com.gateway.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Base64;

public class JwtUtil {

    public static final String SECRET = "R60e9cPo0ZGKhrnjtjZ4FOOhUDVwIi3K9/J47sNms9Y5w7Euyl5H6A95+ajSw46WDo3iMWZrgNkzduMRi7HgxQ=="; // Usa la clave generada aquí

    public static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));
    }

    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Genera una clave segura para HS512
        System.out.println("Generated Key: " + java.util.Base64.getEncoder().encodeToString(key.getEncoded()));
        //System.out.println(Arrays.toString(Base64.getDecoder().decode("eStStU11URQCmZGy0lngmWzXXPcthFiElUZoZYT8PXEtbGmPD1IStxnLB0DyIgMwRDQls7FIrdhnK6kN3TO4GuxSE+bGxZfAMSrV9F03IyA=")));
    }
}
