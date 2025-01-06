package com.order.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderItemRequest {

    private Long productId;
    private Integer quantity;
}
