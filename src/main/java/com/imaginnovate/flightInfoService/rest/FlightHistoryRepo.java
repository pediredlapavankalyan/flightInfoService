package com.imaginnovate.flightInfoService.rest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.imaginnovate.flightInfoService.entities.FlightDate;
import com.imaginnovate.flightInfoService.entities.FlightHistory;

public interface FlightHistoryRepo extends JpaRepository<FlightHistory, FlightDate>{
	
	List<FlightHistory> findByflightno(Integer flghtNo);
	
	@Query("FROM flighthistory fh INNER JOIN fh.operatedflight f "
			+ "WHERE FUNCTION('DATEDIFF', MINUTE, f.arrivaltime, fh.arrivaltime) >= :mins")
	List<FlightHistory> getDelayedFlights(@Param("mins")int mins);
}
