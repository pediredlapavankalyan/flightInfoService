package com.imaginnovate.flightInfoService.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.proxy.HibernateProxy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "cities")
public class City {
	//Entity fields
	@Id
	private String citycode;//Primary key
	private String name;
	private int minutesfromutc;//Universal Time Coordinated
	private String country;
	
	
	
	//list of flights from a partcular city
	//mapped with flight entity
	@OneToMany(mappedBy = "fromcity",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Flight> fromFlights = new ArrayList<Flight>();

	//list of flights to a partcular city
	@OneToMany(mappedBy = "tocity",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Flight> toFlights = new ArrayList<Flight>();
	
	
	


	//getters and setters of all properties
	public String getCityCode() {
		return citycode;
	}

	public void setCityCode(String cityCode) {
		this.citycode = cityCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinutesFromUTC() {
		return minutesfromutc;
	}

	public void setMinutesFromUTC(int minutesFromUTC) {
		this.minutesfromutc = minutesFromUTC;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Flight> getFromFlights() {
		return fromFlights;
	}

	public void setFromFlights(List<Flight> fromFlights) {
		this.fromFlights = fromFlights;
	}

	public List<Flight> getToFlights() {
		return toFlights;
	}

	public void setToFlights(List<Flight> toFlights) {
		this.toFlights = toFlights;
	}

	@Override
	public int hashCode() {
		return Objects.hash(citycode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		return Objects.equals(citycode, other.citycode);
	}

	@Override
	public String toString() {
		return "City [citycode=" + citycode + ", name=" + name + ", minutesfromutc=" + minutesfromutc + ", country="
				+ country + "]";
	}

	public City() {
		super();
	}
	//constructor
	public City(String citycode, String name, int minutesfromutc, String country) {
		super();
		this.citycode = citycode;
		this.name = name;
		this.minutesfromutc = minutesfromutc;
		this.country = country;
	}

}
