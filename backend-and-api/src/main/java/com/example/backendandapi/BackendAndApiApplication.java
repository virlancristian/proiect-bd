package com.example.backendandapi;

import com.example.backendandapi.services.dbservice.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendAndApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendAndApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CarRepository repository) {
		return args -> {
			repository.addCarNativeQuery(2, "Mazda", "RX7", 1999, 2.0F, "DIESEL", 300);
		};
	}
}
