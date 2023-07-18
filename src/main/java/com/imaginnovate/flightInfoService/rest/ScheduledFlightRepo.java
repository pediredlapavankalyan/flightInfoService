package com.imaginnovate.flightInfoService.rest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.imaginnovate.flightInfoService.entities.FlightDate;
import com.imaginnovate.flightInfoService.entities.ScheduledFlight;

import jakarta.transaction.Transactional;

public interface ScheduledFlightRepo extends JpaRepository<ScheduledFlight, FlightDate> {
	ScheduledFlight findByFlightnoAndDeptdate(int flightno, LocalDate deptdate);
	@Transactional
	@Modifying
	void deleteByDeptdateBetween(LocalDate startingDate, LocalDate endingDate);

	@Query("from ScheduledFlight where fromcity.citycode=:fromcity AND deptdate=:deptdate")
	List<ScheduledFlight> findByDeptdateAndCity(@Param("fromcity") String fromcity,
			@Param("deptdate") LocalDate deptdate);
}
