package com.product.infrastructure.adapter.input.rest.exception;

import lombok.Getter;

@Getter
public enum ErrorCatalogEnum {

    PRODUCT_NOT_FOUND("ERR_PROD_001", "Producto no encontrado."),
    PRODUCT_INVALID("ERR_PROD_002", "Informacion incorrecta."),
    PRODUCT_NOT_STOCK("ERR_PROD_003", "Producto sin stock."),
    INTERNAL_ERROR("GEN_ERR_001", "Error interno, comuniquece con el administrador.");

    private String code;
    private String message;

    ErrorCatalogEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
