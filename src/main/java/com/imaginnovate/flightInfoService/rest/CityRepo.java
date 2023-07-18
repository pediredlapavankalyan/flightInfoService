package com.imaginnovate.flightInfoService.rest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imaginnovate.flightInfoService.entities.City;

public interface CityRepo extends JpaRepository<City, String>{

	//finder query
	@Query("from City")
	List<City> getAllCities();  
	
}
