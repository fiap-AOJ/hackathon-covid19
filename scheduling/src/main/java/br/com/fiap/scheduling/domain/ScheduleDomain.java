package br.com.fiap.scheduling.domain;

import java.util.HashSet;
import java.util.Set;

public class ScheduleDomain {

	private final Period period;

	private final Integer capacity;

	private final Set<PatientDomain> patients;

	private ScheduleDomain(final Builder builder){
		this.period = builder.period;
		this.capacity = builder.capacity;
		this.patients = builder.patients;
	}

	public Period getPeriod() {
		return period;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public Set<PatientDomain> getPatients() {
		return patients;
	}

	public void reserveTime(final PatientDomain patient){
		//FIXME: Revisar este ponto para atender um alto volume de concorrência
		// (Se atentar que pode haver multiplas instâncias da app para atender ao volume)
		if(patients.size() < capacity){
			patients.add(patient);
			return;
		}

		throw new IllegalArgumentException(String.format("Este horário já atingiu sua capacidade, não é possível realizar a reserva."));
	}

	public static final class Builder implements Period, Build {
		private static final Integer CAPACITY_DEFAULT = 1;
		private Period period;
		private Integer capacity = CAPACITY_DEFAULT;
		private Set<PatientDomain> patients = new HashSet<>(CAPACITY_DEFAULT);

		@Override
		public Builder period(final Period period) {
			this.period = period;
			return this;
		}

		@Override
		public Build capacity(final Integer capacity) {
			this.capacity = capacity;
			return this;
		}

		@Override
		public Build patients(final Set<PatientDomain> patients) {
			if(patients.size() > capacity){
				throw new IllegalArgumentException(String.format("Este horário não suporta essa capacidade de reservas."));
			}
			this.patients = patients;
			return this;
		}

		@Override
		public ScheduleDomain build() {
			return new ScheduleDomain(this);
		}
	}

	public interface Period{
		public Builder period(final Period period);
	}

	public interface Build{
		public Build capacity(final Integer capacity);
		public Build patients(final Set<PatientDomain> patients);
		public ScheduleDomain build();
	}
}