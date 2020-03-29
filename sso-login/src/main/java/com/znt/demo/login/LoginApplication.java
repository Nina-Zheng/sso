package com.znt.demo.login;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(LoginApplication.class);
        application.run(args);
    }
}
