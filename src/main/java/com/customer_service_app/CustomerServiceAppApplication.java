package com.customer_service_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class CustomerServiceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceAppApplication.class, args);
    }
}
