package br.com.fiap.scheduling.interfaces.converters;

import br.com.fiap.scheduling.domain.Coordinate;
import br.com.fiap.scheduling.interfaces.dtos.LocationDto;
import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class CoordinateToLocationDtoConverter implements Converter<Coordinate, LocationDto> {

	@Override
	public LocationDto convert(final Coordinate source) {
		final LocationDto locationDto = new LocationDto();
		locationDto.setLatitude(source.getLatitude());
		locationDto.setLongitude(source.getLongitude());

		return locationDto;
	}
}
