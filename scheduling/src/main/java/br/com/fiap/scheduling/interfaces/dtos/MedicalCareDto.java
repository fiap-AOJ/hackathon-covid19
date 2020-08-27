package br.com.fiap.scheduling.interfaces.dtos;

public class MedicalCareDto {

	private PatientSituationDto state;

	private PatientDto patient;

	public PatientSituationDto getState() {
		return state;
	}

	public PatientDto getPatient() {
		return patient;
	}

	public void setState(PatientSituationDto state) {
		this.state = state;
	}

	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}
}
