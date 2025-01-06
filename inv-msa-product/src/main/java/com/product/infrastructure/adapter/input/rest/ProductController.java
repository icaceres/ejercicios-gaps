package com.product.infrastructure.adapter.input.rest;

import com.product.application.port.input.ProductServicePort;
import com.product.application.service.OrderItemDto;
import com.product.application.service.ProductDto;
import com.product.infrastructure.adapter.input.rest.mapper.ProductRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductServicePort productService;
    private final ProductRestMapper productRestMapper;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productRestMapper.toProductDto(
                productService.createProduct(productRestMapper.toProduct(productDto))));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productRestMapper.toProductDtoList(productService.getAllProduct()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productRestMapper.toProductDto(productService.getProduct(id)));
    }

    @PutMapping("/{id}/reduceStock")
    public ResponseEntity<String> reduceStock(@PathVariable Long id, @RequestParam Integer amount) {
        productService.reduceStock(id, amount);
        return ResponseEntity.ok("Stock reducido correctamente");
    }

    @PostMapping("/reduceStockByOrderItem")
    public ResponseEntity<List<ProductDto>> reduceStockByOrderItem(@RequestBody List<OrderItemDto> items) {
        return  ResponseEntity.ok(productRestMapper.toProductDtoList(productService.reduceStockByOrderItem(items)));
    }
}
