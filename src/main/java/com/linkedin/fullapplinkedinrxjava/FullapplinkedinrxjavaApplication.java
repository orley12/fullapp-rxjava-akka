package com.linkedin.fullapplinkedinrxjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class FullapplinkedinrxjavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullapplinkedinrxjavaApplication.class, args);
	}

}

