package com.imaginnovate.flightInfoService.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "scheduledflight")
@IdClass(FlightDate.class)
public class ScheduledFlight {

	@Id
	private int flightno;
	@Id
	private LocalDate deptdate;

	private LocalTime depttime;
	private LocalDate arrivaldate;
	private LocalTime arrivaltime;
	private int durationinminutes;

	public ScheduledFlight() {
		super();
	}

	public ScheduledFlight(int flightno, LocalDate deptdate, LocalTime depttime, LocalDate arrivaldate,
			LocalTime arrivaltime, int durationinminutes, City fromcity, City tocity) {
		super();
		this.flightno = flightno;
		this.deptdate = deptdate;
		this.depttime = depttime;
		this.arrivaldate = arrivaldate;
		this.arrivaltime = arrivaltime;
		this.durationinminutes = durationinminutes;
		this.fromcity = fromcity;
		this.tocity = tocity;
	}

	// mapping with city
	@ManyToOne
	@JoinColumn(name = "fromcity")
	@JsonIgnore
	private City fromcity;

	@ManyToOne
	@JoinColumn(name = "tocity")
	@JsonIgnore
	private City tocity;

	// mapping with flight
	@MapsId
	@ManyToOne
	@JoinColumn(name = "flightno", insertable = false, updatable = false)
	@JsonIgnore
	private Flight scheduledflight;

	public int getFlightno() {
		return flightno;
	}

	public void setFlightno(int flightno) {
		this.flightno = flightno;
	}

	public LocalDate getDeptdate() {
		return deptdate;
	}

	public void setDeptdate(LocalDate deptdate) {
		this.deptdate = deptdate;
	}

	public LocalTime getDepttime() {
		return depttime;
	}

	public void setDepttime(LocalTime depttime) {
		this.depttime = depttime;
	}

	public LocalDate getArrivaldate() {
		return arrivaldate;
	}

	public void setArrivaldate(LocalDate arrivaldate) {
		this.arrivaldate = arrivaldate;
	}

	public LocalTime getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(LocalTime arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public int getDurationinminutes() {
		return durationinminutes;
	}

	public void setDurationinminutes(int durationinminutes) {
		this.durationinminutes = durationinminutes;
	}

	public City getFromcity() {
		return fromcity;
	}

	public void setFromcity(City fromcity) {
		this.fromcity = fromcity;
	}

	public City getTocity() {
		return tocity;
	}

	public void setTocity(City tocity) {
		this.tocity = tocity;
	}

	public Flight getScheduledflight() {
		return scheduledflight;
	}

	public void setScheduledflight(Flight scheduledflight) {
		this.scheduledflight = scheduledflight;
	}
	//overriding Object class methods
	@Override
	public int hashCode() {
		return Objects.hash(deptdate, depttime, flightno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduledFlight other = (ScheduledFlight) obj;
		return Objects.equals(deptdate, other.deptdate) && Objects.equals(depttime, other.depttime)
				&& flightno == other.flightno;
	}

	@Override
	public String toString() {
		return "ScheduledFlight [flightno=" + flightno + ", deptdate=" + deptdate + ", depttime=" + depttime
				+ ", arrivaldate=" + arrivaldate + ", arrivaltime=" + arrivaltime + ", durationinminutes="
				+ durationinminutes + ", fromcity=" + fromcity.getName() + ", tocity=" + tocity.getName() + "]";
	}

}
