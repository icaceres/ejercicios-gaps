package com.notification.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.domain.OrderItem;
import com.notification.service.NotificationService;
import com.notification.service.dto.OrderEventResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final ObjectMapper objectMapper;

    public NotificationServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.order}", containerFactory = "kafkaListenerContainerFactory",
            groupId = "group01"
    )
    @Override
    public void consumer(String event) throws JsonProcessingException {
        log.info("Envio de notificacion...");
        log.info("Dato ----->{}", event);
        OrderEventResponse orderEventResponse = objectMapper.readValue(event, OrderEventResponse.class);
        log.info("Id Evento:  {}", orderEventResponse.getId());
        log.info("Fecha:     {}", orderEventResponse.getData().getCreatedAt());
        log.info("Cliente:   {}", orderEventResponse.getData().getCustomerName());
        log.info("Num items: {}", orderEventResponse.getData().getOrderItems().stream().mapToInt(OrderItem::getQuantity).sum());
        log.info("Total:     {}", orderEventResponse.getData().getTotalAmount());
    }
}
