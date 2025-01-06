package com.product.infrastructure.adapter.input.rest.exception;

import com.product.domain.exception.InsufficientStock;
import com.product.domain.exception.ProductNotFoundException;
import com.product.application.service.ErrorMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalControllerException {

    private static final Logger log = LoggerFactory.getLogger(GlobalControllerException.class);

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleProductNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessageDto.builder()
                .code(ErrorCatalogEnum.PRODUCT_NOT_FOUND.getCode())
                .message(ErrorCatalogEnum.PRODUCT_NOT_FOUND.getMessage())
                .build());
    }

    @ExceptionHandler(InsufficientStock.class)
    public ResponseEntity<ErrorMessageDto> handleInsufficientStock() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessageDto.builder()
                .code(ErrorCatalogEnum.PRODUCT_NOT_STOCK.getCode())
                .message(ErrorCatalogEnum.PRODUCT_NOT_STOCK.getMessage())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessageDto.builder()
                .code(ErrorCatalogEnum.PRODUCT_INVALID.getCode())
                .message(ErrorCatalogEnum.PRODUCT_INVALID.getMessage())
                .detail(exception.getBindingResult().getFieldErrors()
                        .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDto> handleException(Exception exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorMessageDto.builder()
                .code(ErrorCatalogEnum.INTERNAL_ERROR.getCode())
                .message(ErrorCatalogEnum.INTERNAL_ERROR.getMessage())
                .detail(Collections.singletonList(exception.getMessage()))
                .build());
    }
}
