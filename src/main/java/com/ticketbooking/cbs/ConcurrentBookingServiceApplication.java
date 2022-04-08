/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The entry point of this application.
 */
@SpringBootApplication
@EnableTransactionManagement
public class ConcurrentBookingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcurrentBookingServiceApplication.class, args);
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
			.info(new Info().title("Ticket booking service API")
				.version("v0.0.1")
				.license(new License().name("Apache 2.0").url("https://github.com/pradeesh-kumar/concurrent-booking-service")));
    }

}
