package com.flight.passengerdetails;

import com.flight.passengerdetails.model.Passenger;
import com.flight.passengerdetails.repository.PassengerRepo;
import com.flight.passengerdetails.service.PassengerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PassengerDetailsApplicationTests {

	@Autowired
	private PassengerServiceImpl passengerServiceImpl;

	@MockBean
	private PassengerRepo passengerRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void setPassengerTest() {

		Passenger passenger = new Passenger();
		passenger.setPassengerId(1);
		passenger.setFirstName("Hritik");
		passenger.setLastName("Ray");
		passenger.setGender("Male");

		Mockito.when(passengerRepo.save(passenger)).thenReturn(passenger);

		assertEquals(passengerServiceImpl.setPassengers(passenger), passenger);
	}

	@Test
	public void getAllPassengerTest() {

		Passenger passenger = new Passenger();
		passenger.setPassengerId(1);
		passenger.setFirstName("Hritik");
		passenger.setLastName("Ray");
		passenger.setGender("Male");

		Passenger passenger2 = new Passenger();
		passenger2.setPassengerId(2);
		passenger2.setFirstName("Ruchika");
		passenger2.setLastName("Ray");
		passenger2.setGender("Female");

		List<Passenger> passengerList = new ArrayList<>();

		passengerList.add(passenger);
		passengerList.add(passenger2);

		Mockito.when(passengerRepo.findAll()).thenReturn(passengerList);

		assertEquals(passengerServiceImpl.getAllPassengers(), passengerList);
	}

	@Test
	public void getPassengersByFirstNameTest() {

		Passenger passenger = new Passenger();
		passenger.setPassengerId(3);
		passenger.setFirstName("Roshni");
		passenger.setLastName("Ray");
		passenger.setGender("Female");

		Mockito.when(passengerRepo.findById(passenger.getPassengerId())).thenReturn(passenger);

		assertEquals(passengerServiceImpl.getPassengersById(3), passenger);

	}

}
