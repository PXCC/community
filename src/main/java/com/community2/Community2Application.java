package com.community2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.community2.mapper")
@SpringBootApplication
public class Community2Application {

	public static void main(String[] args) {
		SpringApplication.run(Community2Application.class, args);
	}


}
