package com.product.application.service;

import com.product.application.port.output.ProductRepositoryPort;
import com.product.domain.exception.ProductNotFoundException;
import com.product.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    private ProductService productService;


    @Test
    void reduceStockByOrderItem_reducesStockSuccessfully() {
        OrderItemDto item1 = new OrderItemDto(1L, 2);
        OrderItemDto item2 = new OrderItemDto(2L, 3);
        List<OrderItemDto> items = Arrays.asList(item1, item2);

        Product product1 = new Product(1L, "SKUP1", "Product1", 10, 0.0);
        Product product2 = new Product(2L, "SKUP2", "Product2", 15, 0.0);

        when(productRepositoryPort.getProduct(1L)).thenReturn(Optional.of(product1));
        when(productRepositoryPort.getProduct(2L)).thenReturn(Optional.of(product2));

        List<Product> result = productService.reduceStockByOrderItem(items);

        assertEquals(2, result.size());
        assertEquals(8, product1.getStock());
        assertEquals(12, product2.getStock());
        verify(productRepositoryPort, times(1)).updateProduct(product1);
        verify(productRepositoryPort, times(1)).updateProduct(product2);
    }

    @Test
    void reduceStockByOrderItem_throwsProductNotFoundException() {
        OrderItemDto item1 = new OrderItemDto(1L, 2);
        List<OrderItemDto> items = List.of(item1);

        when(productRepositoryPort.getProduct(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.reduceStockByOrderItem(items));
    }
}
