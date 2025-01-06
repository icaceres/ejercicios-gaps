package com.product.application.port.output;

import com.product.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    Product createProduct(Product product);
    Optional<Product> getProduct(Long id);
    void updateProduct(Product product);
    List<Product> getAllProduct();
}
