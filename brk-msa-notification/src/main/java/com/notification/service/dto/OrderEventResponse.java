package com.notification.service.dto;

import com.notification.domain.Order;
import com.notification.util.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderEventResponse extends Event<Order> {
}
