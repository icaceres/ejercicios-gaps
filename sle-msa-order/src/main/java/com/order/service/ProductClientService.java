package com.order.service;

import com.order.service.dto.OrderItemRequest;
import com.order.service.dto.ProductResponse;
import com.order.service.impl.InventoryServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "productService", url = "${inv-msa-product.url}", fallbackFactory = InventoryServiceFallbackFactory.class)
public interface ProductClientService {

    @PostMapping("/reduceStockByOrderItem")
    List<ProductResponse> reduceStockByOrderItem(@RequestBody List<OrderItemRequest> items);

}
