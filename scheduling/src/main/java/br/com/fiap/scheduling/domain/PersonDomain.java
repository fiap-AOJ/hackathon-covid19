package br.com.fiap.scheduling.domain;

import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.util.Optional;

public abstract class PersonDomain {

	private String name;

	@MongoId
	private String document;

	private LocalDate birthday;

	private Gender gender;

	protected PersonDomain(final Builder builder){
		this.name = builder.name;
		this.document = builder.document;
		this.birthday = builder.birthday;
		this.gender = builder.gender;
	}

	public String getName() {
		return name;
	}

	public String getDocument() {
		return document;
	}

	public Optional<LocalDate> getBirthday() {
		return Optional.ofNullable(birthday);
	}

	public Optional<Gender> getGender() {
		return Optional.ofNullable(gender);
	}

	public PersonDomain(){}

	public static final Name builderPerson(){
		return new Builder();
	}

	public static class Builder implements Name, Document, Build{
		private String name;
		private String document;
		private LocalDate birthday;
		private Gender gender;

		@Override
		public Document name(final String name) {
			this.name = name;
			return this;
		}

		@Override
		public Builder document(final String document) {
			this.document = document;
			return this;
		}

		@Override
		public Builder birthday(final LocalDate birthday) {
			this.birthday = birthday;
			return this;
		}

		@Override
		public Builder gender(final Gender gender) {
			this.gender = gender;
			return this;
		}
	}

	public interface Name{
		Document name(final String name);
	}

	public interface Document{
		Builder document(final String document);
	}

	public interface Build{
		Builder birthday(final LocalDate birthday);
		Builder gender(final Gender gender);
	}
}