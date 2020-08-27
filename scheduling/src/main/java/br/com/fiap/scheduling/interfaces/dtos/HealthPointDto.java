package br.com.fiap.scheduling.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.*;

public class HealthPointDto {

	@JsonProperty(access = READ_ONLY)
	private String id;

	@NotNull(message = "Campo obrigatório")
	private String name;

	@Valid
	private AddressDto address;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}
}