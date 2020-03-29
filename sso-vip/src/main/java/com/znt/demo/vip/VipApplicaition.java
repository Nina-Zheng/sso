package com.znt.demo.vip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class VipApplicaition {

    public static void main(String[] args){
        SpringApplication application = new SpringApplication(VipApplicaition.class);
        application.run(args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
