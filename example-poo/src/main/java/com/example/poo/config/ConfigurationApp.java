package com.example.poo.config;

import com.example.poo.service.ProductoService;
import com.example.poo.service.impl.ProductoServiceOtherImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationApp {

    @Bean
    public ProductoService productoService() {
        return new ProductoServiceOtherImpl();
    }
}
