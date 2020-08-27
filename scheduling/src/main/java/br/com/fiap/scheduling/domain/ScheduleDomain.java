package br.com.fiap.scheduling.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Document
public class ScheduleDomain {

	@MongoId
	private String id;

	private HealthPointDomain healthPoint;

	private br.com.fiap.scheduling.domain.Period period;

	private Integer capacity;

	private ScheduleDomain(final Builder builder){
		this.id = builder.id;
		this.healthPoint = builder.healthPoint;
		this.period = builder.period;
		this.capacity = builder.capacity;
	}

	public String getId() {
		return id;
	}

	public HealthPointDomain getHealthPoint() {
		return healthPoint;
	}

	public br.com.fiap.scheduling.domain.Period getPeriod() {
		return period;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public ScheduleDomain(){}

	public static final HealthPoint builder(){
		return new Builder();
	}

	public static final class Builder implements HealthPoint, Period, Build {
		private static final Integer CAPACITY_DEFAULT = 1;

		private final String id = UUID.randomUUID().toString();
		private HealthPointDomain healthPoint;
		private br.com.fiap.scheduling.domain.Period period;
		private Integer capacity = CAPACITY_DEFAULT;

		@Override
		public Period healthPoint(final HealthPointDomain healthPointDomain) {
			this.healthPoint = healthPointDomain;
			return this;
		}

		@Override
		public Builder period(final br.com.fiap.scheduling.domain.Period period) {
			this.period = period;
			return this;
		}

		@Override
		public Build capacity(final Integer capacity) {
			this.capacity = capacity;
			return this;
		}

		@Override
		public ScheduleDomain build() {
			return new ScheduleDomain(this);
		}
	}

	public interface HealthPoint{
		public Period healthPoint(final HealthPointDomain healthPointDomain);
	}

	public interface Period{
		public Builder period(final br.com.fiap.scheduling.domain.Period period);
	}

	public interface Build{
		public Build capacity(final Integer capacity);
		public ScheduleDomain build();
	}
}