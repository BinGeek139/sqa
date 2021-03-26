package com.ptit.sqa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.*")
@SpringBootApplication
public class SqaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqaApplication.class, args);
    }

}
