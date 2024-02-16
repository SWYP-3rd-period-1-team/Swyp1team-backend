package com.swig.zigzzang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ZigzzangApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZigzzangApplication.class, args);
	}

}
