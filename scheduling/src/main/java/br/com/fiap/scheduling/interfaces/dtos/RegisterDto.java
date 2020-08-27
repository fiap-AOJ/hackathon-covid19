package br.com.fiap.scheduling.interfaces.dtos;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class RegisterDto {

	@Valid
	@NotNull(message = "Campo obrigatório")
	private LocationDto location;

	public LocationDto getLocation() {
		return location;
	}

	public void setLocation(LocationDto location) {
		this.location = location;
	}

}