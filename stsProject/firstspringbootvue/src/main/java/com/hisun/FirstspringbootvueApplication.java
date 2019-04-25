package com.hisun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hisun.dao")
public class FirstspringbootvueApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstspringbootvueApplication.class, args);
    }

}
