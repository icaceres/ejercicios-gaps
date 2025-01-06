package com.gateway.exception;

import com.gateway.service.dto.ErrorMessageDto;
import com.gateway.util.ErrorCatalogEnum;
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

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorMessageDto> handleAuthException(AuthException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessageDto.builder()
                .code(ErrorCatalogEnum.AUTH_EXCEPTION.getCode())
                .message(ErrorCatalogEnum.AUTH_EXCEPTION.getMessage())
                .detail(Collections.singletonList(exception.getMessage()))
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessageDto.builder()
                .code(ErrorCatalogEnum.AUTH_INVALID_EXCEPTION.getCode())
                .message(ErrorCatalogEnum.AUTH_INVALID_EXCEPTION.getMessage())
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
