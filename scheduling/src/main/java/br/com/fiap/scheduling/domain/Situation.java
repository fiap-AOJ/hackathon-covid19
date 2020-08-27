package br.com.fiap.scheduling.domain;

public enum Situation {

	REGISTER_PENDING("Paciente pendente de registro no sistema"), //

	PENDING_SCHEDULING("Paciente pendente de confirmação de agendamento"), //

	PENDING_VACC("Paciente pendente de vacinação"), //

	VACCINATED("Paciente vacinado");

	private String description;

	private Situation(final String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}