package com.product.infrastructure.adapter.input.rest.mapper;

import com.product.domain.model.Product;
import com.product.application.service.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRestMapper {

    public Product toProduct(ProductDto productDto){
        return Product.builder()
                .id(productDto.getId())
                .sku(productDto.getSku())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .stock(productDto.getStock())
                .build();
    }

    public ProductDto toProductDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

    public List<ProductDto> toProductDtoList(List<Product> productList) {
        return productList.stream().map(this::toProductDto).toList();
    }
}
