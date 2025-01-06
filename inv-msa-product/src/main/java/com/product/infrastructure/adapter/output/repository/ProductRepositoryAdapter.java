package com.product.infrastructure.adapter.output.repository;

import com.product.application.port.output.ProductRepositoryPort;
import com.product.domain.model.Product;
import com.product.infrastructure.adapter.output.repository.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductRepository productRepository;

    public ProductRepositoryAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product createProduct(Product product) {
        ProductEntity productEntity = new ProductEntity(product.getId(), product.getSku(), product.getName(), product.getStock(), product.getPrice());
        ProductEntity  productEntityPersist = productRepository.save(productEntity);
        product.setId(productEntityPersist.getId());
        return product;
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id).map(pe -> new Product(pe.getId(), pe.getSku(), pe.getName(), pe.getStock(), pe.getPrice()));
    }

    @Override
    public void updateProduct(Product product) {
        ProductEntity productEntity = new ProductEntity(product.getId(), product.getSku(), product.getName(), product.getStock(), product.getPrice());
        productRepository.save(productEntity);
    }

    @Override
    public List<Product> getAllProduct() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        return productEntityList.stream().map(pe -> new Product(pe.getId(), pe.getSku(), pe.getName(), pe.getStock(), pe.getPrice())).collect(Collectors.toList());
    }
}
