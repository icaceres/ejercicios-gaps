package com.product.application.port.input;

import com.product.domain.model.Product;
import com.product.application.service.OrderItemDto;

import java.util.List;

public interface ProductServicePort {

    Product createProduct(Product product);

    Product getProduct(Long id);

    void reduceStock(Long id, Integer amount);

    List<Product> getAllProduct();

    List<Product> reduceStockByOrderItem(List<OrderItemDto> items);
}
