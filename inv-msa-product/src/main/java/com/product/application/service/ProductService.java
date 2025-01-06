package com.product.application.service;

import com.product.application.port.output.ProductRepositoryPort;
import com.product.application.port.input.ProductServicePort;
import com.product.domain.exception.ProductNotFoundException;
import com.product.domain.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServicePort {

    private final ProductRepositoryPort productRepositoryPort;

    public ProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepositoryPort.createProduct(product);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepositoryPort.getProduct(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void reduceStock(Long id, Integer amount) {
        Product product = getProduct(id);
        product.reduceStock(amount);
        productRepositoryPort.updateProduct(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepositoryPort.getAllProduct();
    }

    @Transactional
    @Override
    public List<Product> reduceStockByOrderItem(List<OrderItemDto> items) {
        List<Product> products = new ArrayList<>();
        for(OrderItemDto item: items){
            Product product = productRepositoryPort.getProduct(item.getProductId()).orElseThrow(ProductNotFoundException::new);
            reduceStock(product.getId(), item.getQuantity());
            products.add(product);
        }
        return products;
    }
}
