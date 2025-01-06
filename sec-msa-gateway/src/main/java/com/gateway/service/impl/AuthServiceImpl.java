package com.gateway.service.impl;

import com.gateway.exception.AuthException;
import com.gateway.service.AuthService;
import com.gateway.service.dto.AuthDto;
import com.gateway.util.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {


    @Override
    public String generateToken(AuthDto authDto) {
        if(!authDto.getUsername().equals("icaceres") || !authDto.getPassword().equals("12345")){
            throw new AuthException("Credenciales incorrectas.");
        }
        return Jwts.builder()
                .setSubject(authDto.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000L))
                .signWith(JwtUtil.getSecretKey(), SignatureAlgorithm.HS512) // Usa la clave segura
                .compact();
    }
}
