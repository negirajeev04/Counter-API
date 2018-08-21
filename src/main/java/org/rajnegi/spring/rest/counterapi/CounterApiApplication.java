package org.rajnegi.spring.rest.counterapi;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class CounterApiApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(CounterApiApplication.class, args);
	}
	
	
}