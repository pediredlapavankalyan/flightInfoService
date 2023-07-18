package com.imaginnovate.flightInfoService.entities;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Flights")
public class Flight {

	@Id
	private Integer flightno;//Primary key
	@Column(name="durationinminutes")
	private int durationinminutes;
	private LocalTime departuretime;
	private LocalTime arrivaltime;
	private String aircraft;
	@Column(name="fromcity")
	private String from_city;
	@Column(name="tocity")
	private String to_city;

	public String getFrom_city() {
		return from_city;
	}
	public void setFrom_city(String from_city) {
		this.from_city = from_city;
	}
	public String getTo_city() {
		return to_city;
	}
	public void setTo_city(String to_city) {
		this.to_city = to_city;
	}
	public Flight() {
		super();
	}
	//constructor
	public Flight(Integer flightno, int durationinminutes, LocalTime departuretime, LocalTime arrivaltime,
			String aircraft, City fromcity, City tocity) {
		super();
		this.flightno = flightno;
		this.durationinminutes = durationinminutes;
		this.departuretime = departuretime;
		this.arrivaltime = arrivaltime;
		this.aircraft = aircraft;
		this.fromcity = fromcity;
		this.tocity = tocity;
	}
	//mapping with city entity
	@ManyToOne()
	@JoinColumn(name = "fromcity",insertable = false,updatable = false)
	@JsonIgnore
	private City fromcity;

	@ManyToOne()
	@JoinColumn(name = "tocity",insertable = false,updatable = false)
	@JsonIgnore
	private City tocity;
	
	
	//list of scheduled flights
	@OneToMany(mappedBy = "scheduledflight")
	@JsonIgnore
	private List<ScheduledFlight> scheduledFlights = new ArrayList<>();
	
	
	//list of operated flights
	@OneToMany(mappedBy = "operatedflight")
	@JsonIgnore
	private List<FlightHistory> operatedflights = new ArrayList<>();

	//all getter and setter methods
	public Integer getFlightno() {
		return flightno;
	}

	public void setFlightno(Integer flightno) {
		this.flightno = flightno;
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
	public int getDurationinminutes() {
		return durationinminutes;
	}

	public void setDurationinminutes(int durationinminutes) {
		this.durationinminutes = durationinminutes;
	}

	public LocalTime getDeparturetime() {
		return departuretime;
	}

	public void setDeparturetime(LocalTime departuretime) {
		this.departuretime = departuretime;
	}

	public LocalTime getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(LocalTime arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}



	public List<ScheduledFlight> getScheduledFlights() {
		return scheduledFlights;
	}

	public void setScheduledFlights(List<ScheduledFlight> scheduledFlights) {
		this.scheduledFlights = scheduledFlights;
	}

	public List<FlightHistory> getOperatedflights() {
		return operatedflights;
	}

	public void setOperatedflights(List<FlightHistory> operatedflights) {
		this.operatedflights = operatedflights;
	}
	//overriding Object class methods
	@Override
	public int hashCode() {
		return Objects.hash(flightno, fromcity, tocity);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return Objects.equals(flightno, other.flightno) && Objects.equals(fromcity, other.fromcity)
				&& Objects.equals(tocity, other.tocity);
	}

	@Override
	public String toString() {
		return "Flight [flightno=" + flightno + ", durationinminutes=" + durationinminutes + ", departuretime="
				+ departuretime + ", arrivaltime=" + arrivaltime + ", Aircraft=" + aircraft + ", fromcity=" + fromcity
				+ ", tocity=" + tocity + "]";
	}

	
}
