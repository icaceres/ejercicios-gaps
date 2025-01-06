package com.gateway.util;

import lombok.Getter;

@Getter
public enum ErrorCatalogEnum {

    AUTH_EXCEPTION("ERR_AUTH_001", "Error Login."),
    AUTH_INVALID_EXCEPTION("ERR_AUTH_002", "Error Invalid Request."),
    INTERNAL_ERROR("GEN_ERR_001", "Error interno, comuniquece con el administrador.");

    private String code;
    private String message;

    ErrorCatalogEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
