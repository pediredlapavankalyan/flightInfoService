package com.imaginnovate.flightInfoService.rest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.imaginnovate.flightInfoService.entities.City;
import com.imaginnovate.flightInfoService.entities.ScheduledFlight;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.PostConstruct;

@RestController
public class ScheduledFlightController {
	@Autowired
	FlightRepo flightRepo;
	@Autowired
	ScheduledFlightRepo scheduledFlightRepo;

	@PostMapping("/addScFlight")
	@Operation(summary = "To Schedule a flight",description = "Enter Flight no,Departure Date and arraival date to schedule flight")
	public ScheduledFlight addScFlight(@RequestParam() int flightno, @RequestParam() LocalDate fromdate,
			@RequestParam() LocalDate todate) {
		var flight = flightRepo.findById(flightno);
		if (flight.isPresent()) {
			ScheduledFlight s = scheduledFlightRepo.findByFlightnoAndDeptdate(flightno, fromdate);
			if (s != null) {
					throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,
							"flight already scheduled in that date");
			} else {
				var f = flight.get();
				 s = new ScheduledFlight(flightno, fromdate, f.getDeparturetime(), todate,
						f.getArrivaltime(), f.getDurationinminutes(), f.getFromcity(), f.getTocity());
				scheduledFlightRepo.save(s);
				return s;
			}

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "FLight not found");
		}
	}

	@DeleteMapping("/deleteScheduledFlights")
	@Operation(summary  = "To Cancel Flights Betwen dates",description = "Enter Starting date And Ending date to cancel All Flights")
	public String deleteScheduledFlights(@RequestParam() LocalDate startingdate, @RequestParam() LocalDate enddate) {
		scheduledFlightRepo.deleteByDeptdateBetween(startingdate, startingdate);
		return "Deleted successfully";
	}

	@GetMapping("/getFlightsFromCity")
	@Operation(summary  = "To get Scheduled Flights through City code and Departure Date",description = "")
	public List<ScheduledFlight> getFlights(@RequestParam() String fromcity, @RequestParam() LocalDate deptdate) {
		return scheduledFlightRepo.findByDeptdateAndCity(fromcity, deptdate);
	}
}
