package com.flight.airportdetails.controller;

import com.flight.airportdetails.exceptions.RecordAlreadyPresentException;
import com.flight.airportdetails.exceptions.RecordNotFoundException;
import com.flight.airportdetails.model.Airport;
import com.flight.airportdetails.service.AirportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/airport")
public class AirportController {

	Logger logger = LoggerFactory.getLogger(AirportController.class);
	@Autowired(required = true)
	AirportService airportService;

	@GetMapping("/viewAirport/{id}")
	@Operation(description = "Get Airport")
	@ApiResponse(responseCode = "200" , description = "Airport found successfully")
	@ApiResponse(responseCode = "400" , description = "Bad Request: Occurs when Airport isn't present!!")
	@ExceptionHandler(RecordNotFoundException.class)
	public Airport viewAirport(@PathVariable("id") String airportCode) {

		logger.info("[airporttinfo] info message added");
		logger.warn("[airportinfo] warn message added");
		return airportService.viewAirport(airportCode);
	}

	@GetMapping("/allAirport")
	@Operation(description = "Get Airport")
	@ApiResponse(responseCode = "200" , description = "Airport Found successfully")
	@ApiResponse(responseCode = "400" , description = "Bad Request: Occurs when Airport isn't present!!")
	public Iterable<Airport> viewAllAirport() {
		return airportService.viewAllAirport();
	}

	@PostMapping("/addAirport")
	@Operation(description = "Add Airport")
	@ApiResponse(responseCode = "200" , description = "Airport Added successfully")
	@ApiResponse(responseCode = "400" , description = "Bad Request: Occurs when Airport isn't present!!")
	@ExceptionHandler(RecordAlreadyPresentException.class)
	public void addAirport(@RequestBody Airport airport) {
		airportService.addAirport(airport);
	}

	@PutMapping("/updateAirport")
	@Operation(description = "Update Airport")
	@ApiResponse(responseCode = "200" , description = "Airport updated successfully")
	@ApiResponse(responseCode = "400" , description = "Bad Request: Occurs when booking isn't present!!")
	@ExceptionHandler(RecordNotFoundException.class)
	public void modifyAirport(@RequestBody Airport airport) {
		airportService.modifyAirport(airport);
	}

	@DeleteMapping("/deleteAirport/{id}")
	@Operation(description = "Delete Airport")
	@ApiResponse(responseCode = "200" , description = "Airport deleted successfully")
	@ApiResponse(responseCode = "400" , description = "Bad Request: Occurs when airport isn't present!!")
	@ExceptionHandler(RecordNotFoundException.class)
	public void removeAirport(@PathVariable("id") String airportCode) {
		airportService.removeAirport(airportCode);
	}
}