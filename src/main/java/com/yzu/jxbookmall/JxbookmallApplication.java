package com.yzu.jxbookmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.yzu.jxbookmall.dao")
public class JxbookmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(JxbookmallApplication.class, args);
    }

}
