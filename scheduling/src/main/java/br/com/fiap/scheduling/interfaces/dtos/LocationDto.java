package br.com.fiap.scheduling.interfaces.dtos;

import javax.validation.constraints.NotNull;

public class LocationDto {

	@NotNull(message = "Campo obrigatório")
	private Double latitude;

	@NotNull(message = "Campo obrigatório")
	private Double longitude;

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
