package br.com.fiap.scheduling.domain;

import java.util.Optional;

public class AddressDomain {

	private final br.com.fiap.scheduling.domain.Coordinate coordinate;

	private final String street;

	private final String number;

	private final String city;

	private final String neighborhood;

	private final String state;

	private AddressDomain(final Builder builder){
		this.coordinate = builder.coordinate;
		this.street = builder.street;
		this.number = builder.number;
		this.city = builder.city;
		this.neighborhood = builder.neighborhood;
		this.state = builder.state;
	}

	public static final AddressDomain of(final br.com.fiap.scheduling.domain.Coordinate coordinate){
		return builder() //
				.coordinate(coordinate) //
				.build();
	}

	public br.com.fiap.scheduling.domain.Coordinate getCoordinate() {
		return coordinate;
	}

	public Optional<String> getStreet() {
		return Optional.ofNullable(street);
	}

	public Optional<String> getNumber() {
		return Optional.ofNullable(number);
	}

	public Optional<String> getCity() {
		return Optional.ofNullable(city);
	}

	public Optional<String> getNeighborhood() {
		return Optional.ofNullable(neighborhood);
	}

	public Optional<String> getState() {
		return Optional.ofNullable(state);
	}

	public static final Builder builder(){
		return new Builder();
	}

	public static final class Builder implements Coordinate, Build{
		private br.com.fiap.scheduling.domain.Coordinate coordinate;
		private String street;
		private String number;
		private String city;
		private String neighborhood;
		private String state;

		@Override
		public Build coordinate(final br.com.fiap.scheduling.domain.Coordinate coordinate) {
			this.coordinate = coordinate;
			return this;
		}

		@Override
		public Build street(final String street) {
			this.street = street;
			return this;
		}

		@Override
		public Build number(final String number) {
			this.number = number;
			return this;
		}

		@Override
		public Build city(final String city) {
			this.city = city;
			return this;
		}

		@Override
		public Build neighborhood(final String neighborhood) {
			this.neighborhood = neighborhood;
			return this;
		}

		@Override
		public Build state(final String state) {
			this.state = state;
			return this;
		}

		@Override
		public AddressDomain build() {
			return new AddressDomain(this);
		}
	}

	public interface Coordinate{
		public Build coordinate(final br.com.fiap.scheduling.domain.Coordinate coordinate);
	}

	public interface Build{
		public Build street(final String street);
		public Build number(final String number);
		public Build city(final String city);
		public Build neighborhood(final String neighborhood);
		public Build state(final String state);
		public AddressDomain build();
	}
}