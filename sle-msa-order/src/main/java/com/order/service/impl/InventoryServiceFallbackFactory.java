package com.order.service.impl;

import com.order.service.ProductClientService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class InventoryServiceFallbackFactory implements FallbackFactory<ProductClientService> {

        @Override
        public ProductClientService create(Throwable cause) {
            return items -> Collections.emptyList();
        }
}
