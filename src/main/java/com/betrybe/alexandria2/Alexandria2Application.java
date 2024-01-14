package com.betrybe.alexandria2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@ComponentScan("com.betrybe.alexandria2")
public class Alexandria2Application {

	public static void main(String[] args) {
		SpringApplication.run(Alexandria2Application.class, args);
	}

}
