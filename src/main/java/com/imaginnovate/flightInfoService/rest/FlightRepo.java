package com.imaginnovate.flightInfoService.rest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.imaginnovate.flightInfoService.entities.Flight;

public interface FlightRepo extends JpaRepository<Flight, Integer > {
	@Query("from Flight where fromcity.citycode=:fromCity and tocity.citycode	=:toCity")
	List<Flight> getFlightsBetweenCities(@Param("fromCity")String fromCity,@Param("toCity")String toCity);
	
	
}
