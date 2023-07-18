package com.imaginnovate.flightInfoService.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.imaginnovate.flightInfoService.entities.City;

@RestController
public class CityController {

	@Autowired
	private CityRepo cityRepo;

	// to get all city names in list collection
	@GetMapping("/CityList")
	public List<City> getCities() {
		return cityRepo.getAllCities();
	}

	// to add a new city
	@PostMapping("/addcity")
	public City addCity(@org.springframework.web.bind.annotation.RequestBody City city) {
		// condition to check weather city exists in city table or not by using
		// cityCode(Primary key)
		if (cityRepo.findById(city.getCityCode()).isPresent())
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "City already exists");
		else {
			cityRepo.save(city);// adding new city row into table(Persist)
			return city;
		}
	}

	// to delete a city by id
	@DeleteMapping("/deletecity/{id}")
	public void deleteCity(@PathVariable("id") String id) {
		if (cityRepo.findById(id).isPresent())
			cityRepo.deleteById(id);// crud repostory method
		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "city not found with that id");
	}

	// updating city
	@PutMapping("/updatecity")
	public City updateCity(@RequestParam() String id, @RequestParam() int minutesfromutc) {
		if (cityRepo.findById(id).isPresent()) {
			var city = cityRepo.findById(id).get();
			city.setMinutesFromUTC(minutesfromutc);
			cityRepo.save(city);// saving changes in database
			return city;
		} else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "city not found with that id");
	}
}
