package com.order.service.dto;

import com.order.domain.Order;
import com.order.util.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderEventResponse  extends Event<Order> {
}
