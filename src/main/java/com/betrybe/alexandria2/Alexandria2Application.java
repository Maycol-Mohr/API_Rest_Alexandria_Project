package com.betrybe.alexandria2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan("com.betrybe.alexandria2")
public class Alexandria2Application {

	public static void main(String[] args) {
		SpringApplication.run(Alexandria2Application.class, args);
	}
}