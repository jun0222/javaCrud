package com.example.javaCrud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.config", "com.example.javaCrud"})
@MapperScan("com.example.javaCrud.mapper")
public class JavaCrudApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaCrudApplication.class, args);
    }
}
