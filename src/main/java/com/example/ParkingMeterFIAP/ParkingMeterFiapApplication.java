package com.example.ParkingMeterFIAP;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API Parking Meter",
				description = "Tech Challeng - FIAP 2024 - Grupo 18",
				version = "1.0.0"
		)
)
public class ParkingMeterFiapApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingMeterFiapApplication.class, args);
	}

}
