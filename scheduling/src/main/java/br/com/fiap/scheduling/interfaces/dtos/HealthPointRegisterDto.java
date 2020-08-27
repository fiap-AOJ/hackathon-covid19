package br.com.fiap.scheduling.interfaces.dtos;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class HealthPointRegisterDto extends  RegisterDto{

	@Valid
	@NotNull(message = "Campo obrigatório")
	private HealthPointDto healthPoint;

	public HealthPointDto getHealthPoint() {
		return healthPoint;
	}

	public void setHealthPoint(HealthPointDto healthPoint) {
		this.healthPoint = healthPoint;
	}
}
