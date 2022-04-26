package com.sparta.week2level2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Week2level2Application {

    public static void main(String[] args) {
        SpringApplication.run(Week2level2Application.class, args);
    }

}