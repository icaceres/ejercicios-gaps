package com.order.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.domain.Order;
import com.order.service.OrderNotificationService;
import com.order.service.dto.OrderEventResponse;
import com.order.util.EventType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class OrderNotificationServiceImpl implements OrderNotificationService {

    private final KafkaTemplate<String, String> producer;

    private final ObjectMapper objectMapper;

    @Value("${spring.kafka.topic.order}")
    private String orderProductTopic;

    public OrderNotificationServiceImpl(KafkaTemplate<String, String> producer, ObjectMapper objectMapper) {
        this.producer = producer;
        this.objectMapper = objectMapper;
    }

    @Override
    public void notify(Order order, EventType eventType) throws JsonProcessingException {
        OrderEventResponse orderEventResponse = new OrderEventResponse();
        orderEventResponse.setData(order);
        orderEventResponse.setId(UUID.randomUUID().toString());
        orderEventResponse.setType(eventType);
        orderEventResponse.setDate(LocalDate.now());

        String jsonOrderEventResponse = objectMapper.writeValueAsString(orderEventResponse);

        producer.send(orderProductTopic, jsonOrderEventResponse);
    }
}
