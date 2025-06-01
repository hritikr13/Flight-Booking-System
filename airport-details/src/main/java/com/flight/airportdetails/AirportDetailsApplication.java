package com.flight.airportdetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AirportDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportDetailsApplication.class, args);
	}

}
