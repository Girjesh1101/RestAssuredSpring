package com.testing.SpringProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringProjectApplication {

//  @SpringBootApplication --> uses
//  @Configuration
//  @EnableAutoConfiguration
//  @ComponentScan


	public static void main(String[] args) {
		SpringApplication.run(SpringProjectApplication.class, args);
		
	}

}
