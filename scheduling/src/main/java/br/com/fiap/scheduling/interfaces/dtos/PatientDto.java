package br.com.fiap.scheduling.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PatientDto {

	@NotNull(message = "Campo obrigatório")
	private String name;

	@NotNull(message = "Campo obrigatório")
	@CPF(message = "CPF não é válido")
	private String document;

	@NotNull(message = "Campo obrigatório")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;

	@NotNull(message = "Campo obrigatório")
	private String gender;

	private Boolean hasRisk = Boolean.FALSE;

	private Boolean isHealthProfessional = Boolean.FALSE;

	public String getName() {
		return name;
	}

	public String getDocument() {
		return document;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public String getGender() {
		return gender;
	}

	public Boolean getHasRisk() {
		return hasRisk;
	}

	@JsonProperty("isHealthProfessional")
	public Boolean getHealthProfessional() {
		return isHealthProfessional;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setHasRisk(Boolean hasRisk) {
		this.hasRisk = hasRisk;
	}

	public void setHealthProfessional(Boolean healthProfessional) {
		isHealthProfessional = healthProfessional;
	}
}
