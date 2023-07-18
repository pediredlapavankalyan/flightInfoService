package com.imaginnovate.flightInfoService.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.imaginnovate.flightInfoService.entities.FlightHistory;

import jakarta.websocket.server.PathParam;

@RestController
public class FlightHistoryController {
	@Autowired
	FlightHistoryRepo flightHistoryRepo;
	@Autowired
	FlightRepo flightRepo;

	@GetMapping("/flighthistorybyflightno/{flightno}")
	public List<FlightHistory> getFlightHistoryByFlightNo(@PathVariable("flightno") Integer flightno) {
		return flightHistoryRepo.findByflightno(flightno);
	}

	@GetMapping("/flighthistory/{flightno}")
	public List<FlightHistory> getFlightHistoryByid(@PathVariable("flightno") Integer flightno) {
		var flight = flightRepo.findById(flightno);
		if (flight.isPresent())
			return flight.get().getOperatedflights();
		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "flight with flightno not found");
	}

	@GetMapping("/getdelayedflights/{mins}")
	public List<FlightHistory> getDelayedlights(@PathVariable("mins") int mins) {
		return flightHistoryRepo.getDelayedFlights(mins);
	}
}
