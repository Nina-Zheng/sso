package com.znt.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CartApplication {

    public static void main(String[] args){
        SpringApplication application = new SpringApplication(CartApplication.class);
        application.run(args);
    }

    //项目启动的时候，生成RestTemplate对象
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
