package com.desafio.uno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.desafio.uno.client"})
public class DesafioUnoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioUnoApplication.class, args);
	}

}
