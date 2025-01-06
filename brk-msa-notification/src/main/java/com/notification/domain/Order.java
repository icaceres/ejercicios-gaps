package com.notification.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {

    private Long id;

    private String customerName;

    private BigDecimal totalAmount;

    private LocalDateTime createdAt;

    private List<OrderItem> orderItems;
}
