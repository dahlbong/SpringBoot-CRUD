package com.example.realjungleboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:.env")
public class RealJungleBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealJungleBoardApplication.class, args);
    }

}
