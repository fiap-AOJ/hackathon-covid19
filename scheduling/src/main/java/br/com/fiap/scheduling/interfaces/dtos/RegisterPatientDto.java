package br.com.fiap.scheduling.interfaces.dtos;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class RegisterPatientDto extends  RegisterDto{

	@Valid
	@NotNull(message = "Campo obrigatório")
	private PatientDto patient;

	public PatientDto getPatient() {
		return patient;
	}

	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}

}
