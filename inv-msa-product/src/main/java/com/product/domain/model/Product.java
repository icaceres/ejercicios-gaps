package com.product.domain.model;

import com.product.domain.exception.InsufficientStock;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    private Long id;
    private String sku;
    private String name;
    private Integer stock;
    private Double price;

    public void reduceStock(Integer amount) {
        if (amount > stock) {
            throw new InsufficientStock();
        }
        stock -= amount;
    }
}
