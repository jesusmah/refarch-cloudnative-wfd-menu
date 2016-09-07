package com.ibm.microservices.wfd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class MenuApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenuApplication.class, args);
		System.out.println("Running "+MenuApplication.class+" via Spring Boot!");
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
    return new RestTemplate();
	}
}
