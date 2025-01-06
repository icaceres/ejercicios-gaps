package com.notification.domain;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {

    private Long id;

    private Long productId;

    private Integer quantity;

    private BigDecimal price;
}
