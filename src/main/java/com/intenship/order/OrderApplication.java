package com.intenship.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderApplication {

    @Bean
    public WebClient.Builder getWebClintBuilder() {
        return WebClient.builder();
    }


    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
