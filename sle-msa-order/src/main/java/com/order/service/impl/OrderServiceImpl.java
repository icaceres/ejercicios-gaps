package com.order.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.domain.Order;
import com.order.domain.OrderItem;
import com.order.exception.ProductServiceException;
import com.order.repository.OrderRepository;
import com.order.service.OrderNotificationService;
import com.order.service.OrderService;
import com.order.service.ProductClientService;
import com.order.service.dto.OrderItemRequest;
import com.order.service.dto.ProductResponse;
import com.order.util.EventType;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private ProductClientService productClientService;

    private OrderNotificationService orderNotificationService;

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAllWithItems(pageable);
    }

    @Transactional
    @Override
    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackCreateOrder")
    public Order createOrder(String customerName, List<OrderItemRequest> items) throws JsonProcessingException {

        List<ProductResponse> products = productClientService.reduceStockByOrderItem(items);

        Order order = new Order();
        order.setOrderItems(new ArrayList<>());
        order.setCustomerName(customerName);
        order.setCreatedAt(LocalDateTime.now());



        BigDecimal totalAmount = BigDecimal.ZERO;

        for(OrderItemRequest itemRequest: items){
            ProductResponse product = products.stream().filter(p -> p.getId().equals(itemRequest.getProductId())).findFirst().orElseThrow(() -> new RuntimeException("No se encuentra el producto."));
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(itemRequest.getProductId());
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(BigDecimal.valueOf(product.getPrice()));
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);

            totalAmount = totalAmount.add(orderItem.getPrice());

        }
        order.setTotalAmount(totalAmount);
        Order orderPersist = orderRepository.save(order);
        orderNotificationService.notify(orderPersist, EventType.CREATED);
        return orderPersist;
    }

    public Order fallbackCreateOrder(String customerName, List<OrderItemRequest> items, Throwable throwable) {
        throw new ProductServiceException("El servicio de productos no está disponible. Por favor intentelo más tarde.");
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
