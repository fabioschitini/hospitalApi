package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@EnableFeignClients
@EnableJpaAuditing
@EnableJpaRepositories
//@ComponentScan(basePackages = { "my.package.base.*" })
//@EntityScan("my.package.base.*")   
@ComponentScan({ "com.example.demo.*" })
@ComponentScan({ "com.example.demo.repositories" })

public class ConsultorioApplication { 

	public static void main(String[] args) {
		SpringApplication.run(ConsultorioApplication.class, args);
	}

} 
