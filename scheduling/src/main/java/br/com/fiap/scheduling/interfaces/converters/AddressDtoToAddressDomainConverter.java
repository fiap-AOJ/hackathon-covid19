package br.com.fiap.scheduling.interfaces.converters;

import br.com.fiap.scheduling.domain.AddressDomain;
import br.com.fiap.scheduling.domain.Coordinate;
import br.com.fiap.scheduling.interfaces.dtos.AddressDto;
import br.com.fiap.scheduling.interfaces.dtos.LocationDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Lazy
@Component
public class AddressDtoToAddressDomainConverter implements Converter<AddressDto, AddressDomain> {

	@Override
	public AddressDomain convert(final AddressDto source) {
		final Coordinate coordinate = buildCoordinate(source);
		if(isNull(source.getStreet())){
			return buildAddressDomain(coordinate);
		}

		return AddressDomain.builder() //
					.coordinate(coordinate) //
					.city(source.getCity()) //
					.neighborhood(source.getNeighborhood()) //
					.number(source.getNumber()) //
					.street(source.getStreet()) //
					.state(source.getState()) //
					.build();
	}

	private AddressDomain buildAddressDomain(final Coordinate coordinate) {
		return AddressDomain.of(coordinate);
	}

	private Coordinate buildCoordinate(final AddressDto source) {
		final LocationDto locationDto = source.getLocation();
		return Coordinate.of(locationDto.getLatitude(), locationDto.getLongitude());
	}
}
