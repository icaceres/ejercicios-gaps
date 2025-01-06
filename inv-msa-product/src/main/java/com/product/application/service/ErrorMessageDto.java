package com.product.application.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ErrorMessageDto {

    private String code;
    private String message;
    private List<String> detail;
}
