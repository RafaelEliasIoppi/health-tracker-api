package com.rafael.healthtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
    servers = {
        @Server(url = "https://jubilant-space-xylophone-pjrwr664qpw5c9rq4-3335.app.github.dev/")
    }
)

@SpringBootApplication
public class HealthTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthTrackerApiApplication.class, args);
	}

}
