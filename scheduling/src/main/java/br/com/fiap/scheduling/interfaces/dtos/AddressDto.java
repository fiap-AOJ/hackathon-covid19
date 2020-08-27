package br.com.fiap.scheduling.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

public class AddressDto {

	@JsonProperty(access = READ_ONLY)
	private LocationDto location;

	private String street;

	private String number;

	private String city;

	private String neighborhood;

	private String state;

	public LocationDto getLocation() {
		return location;
	}

	public String getStreet() {
		return street;
	}

	public String getNumber() {
		return number;
	}

	public String getCity() {
		return city;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public String getState() {
		return state;
	}

	public void setLocation(LocationDto location) {
		this.location = location;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public void setState(String state) {
		this.state = state;
	}
}
