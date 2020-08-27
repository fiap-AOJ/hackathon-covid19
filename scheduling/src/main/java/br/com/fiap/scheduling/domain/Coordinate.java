package br.com.fiap.scheduling.domain;

public class Coordinate {

	private Double latitude;

	private Double longitude;

	private Coordinate(final Double latitude, final Double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Coordinate(){}

	public static final Coordinate of(final Double latitude, final Double longitude){
		return new Coordinate(latitude, longitude);
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}
}