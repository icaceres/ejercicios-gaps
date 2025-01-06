package com.product.application.service;

import com.product.infrastructure.adapter.util.SkuConstrain;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    @NotBlank(message = "sku no debe estar vacío")
    @Pattern(regexp = "^[A-Z]{3}-[A-Z]{4}-\\d{4}[A-Z]{3}$", message = "SKU debe seguir el patrón XXX-XXXX-0000XXX")
    @SkuConstrain
    private String sku;

    @NotBlank(message = "name no debe estar vacío")
    private String name;

    @Min(value = 1, message = "stock debe ser mayor que o igual a 1")
    private Integer stock;

    @NotNull(message = "Price no debe ser nulo")
    @Digits(integer = 10, fraction = 2, message = "Debe ser un número con hasta 10 dígitos enteros y 2 decimales")
    private Double price;
}
