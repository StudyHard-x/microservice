package com.microstudy.users;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients(basePackages = "com.microstudy.users.feign")
@EnableDiscoveryClient
@MapperScan("com.microstudy.users.dao")
@SpringBootApplication
public class LeaveUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeaveUsersApplication.class, args);
    }

    //注入ribbon调用类RestTemplate
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
