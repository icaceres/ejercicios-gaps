package com.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.domain.Order;
import com.order.service.dto.OrderItemRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Order findById(Long id);

    Page<Order> findAll(Pageable pageable);

    Order createOrder(String customerName, List<OrderItemRequest> items) throws JsonProcessingException;

    void deleteById(Long id);
}
