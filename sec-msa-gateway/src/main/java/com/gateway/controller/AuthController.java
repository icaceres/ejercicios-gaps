package com.gateway.controller;

import com.gateway.service.AuthService;
import com.gateway.service.dto.AuthDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/token")
    public String generateToken(@RequestBody @Valid AuthDto authDto) {
        return authService.generateToken(authDto);
    }

}
