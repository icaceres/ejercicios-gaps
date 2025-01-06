package com.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.domain.Order;
import com.order.service.OrderService;
import com.order.service.dto.OrderItemRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@AllArgsConstructor
@Controller
public class OrderController {

    private OrderService orderService;

    @QueryMapping(name = "findAllOrders")
    public Page<Order> findAll(@Argument Integer page, @Argument Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderService.findAll(pageable);
    }

    @QueryMapping(name = "findOrderById")
    public Order findById(@Argument(name = "orderId") String id){
        return orderService.findById(Long.parseLong(id));
    }

    @MutationMapping
    public Order createOrder(@Argument String customerName, @Argument List<OrderItemRequest> items) throws JsonProcessingException {
        return orderService.createOrder(customerName, items);
    }
}
