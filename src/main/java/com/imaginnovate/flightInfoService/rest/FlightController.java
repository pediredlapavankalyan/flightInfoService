package com.imaginnovate.flightInfoService.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.flightInfoService.entities.Flight;

@RestController
public class FlightController {
	// Dependency injection
	@Autowired
	FlightRepo flightRepo;
	@Autowired
	CityRepo cityRepo;

	// listing flights between cities
	@GetMapping("/flightsBetweenCities")
	public List<Flight> flightBetweenCities(@RequestParam() String fromCity, @RequestParam() String toCity) {
		return flightRepo.getFlightsBetweenCities(fromCity, toCity);
	}

	// Listing flights using Pageable interface
	@GetMapping("/flights/{pagenum}")
	public List<Flight> getFlights(@PathVariable("pagenum") int pagenum) {
		return flightRepo.findAll(PageRequest.of(pagenum, 5)).getContent();
	}

	// Adding New Flight
//	@PostMapping("/addflight")
//	public Flight addFlight(@RequestParam() int flightno, @RequestParam() String fromcity,
//			@RequestParam() String tocity, @RequestParam() int durationinminutes,
//			@RequestParam() LocalTime departuretime, @RequestParam() LocalTime arrivaltime,
//			@RequestParam() String aircraft) {
//		if (flightRepo.findById(flightno).isPresent())
//			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "flight already exists");
//		else {
//			var fromCity = cityRepo.findById(fromcity);
//			var toCity = cityRepo.findById(tocity);
//			if (fromCity.isPresent() && toCity.isPresent()) {
//				Flight f = new Flight(flightno, durationinminutes, departuretime, arrivaltime, aircraft, fromCity.get(),
//						toCity.get());
//				flightRepo.save(f);
//				return f;
//			} else
//				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cities data not found");
//		}
//	}
	@PostMapping("/addflight")
	public Flight addflight(@RequestBody Flight flight)
	{
		flightRepo.save(flight);
		return flight;
	}

	
}
