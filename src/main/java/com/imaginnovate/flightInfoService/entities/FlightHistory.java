package com.imaginnovate.flightInfoService.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import org.hibernate.proxy.HibernateProxy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity(name="flighthistory")
@Table(name = "flightshistory")
@IdClass(FlightDate.class)
public class FlightHistory {

	@Id
	private int flightno;//Primary key
	@Id
	private LocalDate deptdate;
	private LocalTime depttime;
	private LocalDate arrivaldate;
	private LocalTime arrivaltime;
	private int durationinminutes;
	private String aircraft;
	private String remarks;
	

	// mapping with city Entity
	@ManyToOne()
	@JoinColumn(name = "fromcity")
	@JsonIgnore
	private City fromcity;
	@ManyToOne()
	@JoinColumn(name = "tocity")
	@JsonIgnore
	private City tocity;

	// mapping with flight
	@ManyToOne()
	@JoinColumn(name = "flightno", insertable = false, updatable = false)
	@JsonIgnore
	private Flight operatedflight;
	
	//all getter and setter methods
	public int getFlightNo() {
		return flightno;
	}

	public void setFlightNo(int flightNo) {
		this.flightno = flightNo;
	}

	public LocalDate getDeptDate() {
		return deptdate;
	}

	public void setDeptDate(LocalDate deptDate) {
		this.deptdate = deptDate;
	}

	public LocalTime getDeptTime() {
		return depttime;
	}

	public void setDeptTime(LocalTime deptTime) {
		this.depttime = deptTime;
	}

	public LocalDate getArrivalDate() {
		return arrivaldate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivaldate = arrivalDate;
	}

	public LocalTime getArrivalTime() {
		return arrivaltime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivaltime = arrivalTime;
	}

	public int getDurationInMinutes() {
		return durationinminutes;
	}

	public void setDurationInMinutes(int durationInMinutes) {
		this.durationinminutes = durationInMinutes;
	}

	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public City getFromCity() {
		return fromcity;
	}

	public void setFromCity(City fromCity) {
		this.fromcity = fromCity;
	}

	public City getToCity() {
		return tocity;
	}

	public void setToCity(City toCity) {
		this.tocity = toCity;
	}

	public Flight getOperatedFlight() {
		return operatedflight;
	}

	public void setOperatedFlight(Flight operatedFlight) {
		this.operatedflight = operatedFlight;
	}
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
		FlightHistory other = (FlightHistory) obj;
		return Objects.equals(deptdate, other.deptdate) && flightno == other.flightno;
	}

	@Override
	public String toString() {
		return "FlightHistory [flightNo=" + flightno + ", deptDate=" + deptdate + ", deptTime=" + depttime
				+ ", arrivalDate=" + arrivaldate + ", arrivalTime=" + arrivaltime + ", durationInMinutes="
				+ durationinminutes + ", aircraft=" + aircraft + ", remarks=" + remarks + ", fromCity=" + fromcity.getName()
				+ ", toCity=" + tocity.getName() + "]";
	}

}
