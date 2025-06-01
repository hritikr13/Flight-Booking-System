package com.flight.airportdetails.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.flight.airportdetails.model.Airport;

@Repository
public interface AirportDao extends CrudRepository<Airport, String> {

}