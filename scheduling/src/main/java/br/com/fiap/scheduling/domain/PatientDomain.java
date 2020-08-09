package br.com.fiap.scheduling.domain;

public final class PatientDomain extends PersonDomain{

	private final Boolean hasRisk;

	private final Boolean isHealthProfessional;

	private PatientDomain(final Builder builder){
		super(builder.person);
		this.hasRisk = builder.hasRisk;
		this.isHealthProfessional = builder.isHealthProfessional;
	}

	public Boolean hasRisk() {
		return hasRisk;
	}

	public Boolean isHealthProfessional() {
		return isHealthProfessional;
	}

	public static final Builder builder(){
		return new Builder();
	}

	public static class Builder implements PersonDomainBuilder, Build {
		private PersonDomain.Builder person;
		private Boolean hasRisk = Boolean.FALSE;
		private Boolean isHealthProfessional = Boolean.FALSE;

		@Override
		public Build person(final PersonDomain.Builder person) {
			this.person = person;
			return this;
		}

		@Override
		public Build hasRisk(final Boolean hasRisk) {
			this.hasRisk = hasRisk;
			return this;
		}

		@Override
		public Build isHealthProfessional(final Boolean isHealthProfessional) {
			this.isHealthProfessional = isHealthProfessional;
			return this;
		}

		@Override
		public PatientDomain build() {
			return new PatientDomain(this);
		}
	}

	public interface PersonDomainBuilder{
		public Build person(final PersonDomain.Builder person);
	}

	public interface Build{
		public Build hasRisk(final Boolean hasRisk);
		public Build isHealthProfessional(final Boolean isHealthProfessional);
		public PatientDomain build();
	}
}