package com.gateway.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthDto {

    @NotBlank(message = "username es requerido.")
    private String username;

    @NotBlank(message = "password es requerido.")
    private String password;
}
