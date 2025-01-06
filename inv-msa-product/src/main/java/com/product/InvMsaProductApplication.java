package com.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvMsaProductApplication {

	private static final Logger log = LoggerFactory.getLogger(InvMsaProductApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InvMsaProductApplication.class, args);
		log.warn("Product service start!!!");
	}

}
