package br.com.fiap.scheduling.interfaces.dtos;

public class PatientSituationDto {

	private String state;

	private String description;

	public String getState() {
		return state;
	}

	public String getDescription() {
		return description;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
