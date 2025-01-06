package com.gateway.service;

import com.gateway.service.dto.AuthDto;

public interface AuthService {

    String generateToken(AuthDto authDto);
}
