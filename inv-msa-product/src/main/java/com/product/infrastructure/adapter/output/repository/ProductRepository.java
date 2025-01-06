package com.product.infrastructure.adapter.output.repository;

import com.product.infrastructure.adapter.output.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findBySku(String sku);
}
