package com.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.domain.Order;
import com.order.util.EventType;

public interface OrderNotificationService {

    void notify(Order order, EventType eventType) throws JsonProcessingException;
}
