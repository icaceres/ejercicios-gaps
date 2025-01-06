package com.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SleMsaOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleMsaOrderApplication.class, args);
	}

}
