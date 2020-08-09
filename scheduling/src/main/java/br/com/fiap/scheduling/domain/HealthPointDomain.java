package br.com.fiap.scheduling.domain;

import java.util.HashSet;
import java.util.Set;

public class HealthPointDomain {

	private final String name;

	private final AddressDomain address;

	private final Set<ScheduleDomain> schedules;

	private HealthPointDomain(final Builder builder){
		this.name = builder.name;
		this.address = builder.address;
		this.schedules = builder.schedules;
	}

	public static final Builder build(){
		return new Builder();
	}

	public static final class Builder implements Name, Address, Build{
		private String name;
		private AddressDomain address;
		private Set<ScheduleDomain> schedules = new HashSet<>();

		@Override
		public Address name(final String name) {
			this.name = name;
			return this;
		}

		@Override
		public Builder address(final AddressDomain address) {
			this.address = address;
			return this;
		}

		@Override
		public Builder schedules(final Set<ScheduleDomain> schedulies) {
			this.schedules = schedulies;
			return this;
		}

		@Override
		public HealthPointDomain build() {
			return null;
		}
	}

	public interface Name{
		public Address name(final String name);
	}

	public interface Address{
		public Builder address(final AddressDomain address);
	}

	public interface Build{
		public Builder schedules(final Set<ScheduleDomain> schedules);
		public HealthPointDomain build();
	}
}