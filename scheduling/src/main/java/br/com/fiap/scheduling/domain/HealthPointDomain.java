package br.com.fiap.scheduling.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Document
public class HealthPointDomain {

	@MongoId
	private String id;

	private String name;

	private AddressDomain address;

	private HealthPointDomain(final Builder builder){
		this.id = builder.id;
		this.name = builder.name;
		this.address = builder.address;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public AddressDomain getAddress() {
		return address;
	}

	public HealthPointDomain(){}

	public static final Name builder(){
		return new Builder();
	}

	public static final class Builder implements Name, Address, Build{
		private final String id = UUID.randomUUID().toString();
		private String name;
		private AddressDomain address;

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
		public HealthPointDomain build() {
			return new HealthPointDomain(this);
		}
	}

	public interface Name{
		public Address name(final String name);
	}

	public interface Address{
		public Builder address(final AddressDomain address);
	}

	public interface Build{
		public HealthPointDomain build();
	}
}