package com.imaginnovate.flightInfoService.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
//class for composite key in scheduled flight and flighthistory classes
@Embeddable
public class FlightDate implements Serializable {

	@Column(name = "flightno")
	private int flightno;

	@Column(name = "deptdate")
	private LocalDate deptdate;
	//overriding Object class methods
	@Override
	public int hashCode() {
		return Objects.hash(deptdate, flightno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightDate other = (FlightDate) obj;
		return Objects.equals(deptdate, other.deptdate) && flightno == other.flightno;
	}

}
