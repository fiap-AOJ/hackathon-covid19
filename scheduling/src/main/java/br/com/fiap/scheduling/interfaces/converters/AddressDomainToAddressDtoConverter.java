package br.com.fiap.scheduling.interfaces.converters;

import br.com.fiap.scheduling.domain.AddressDomain;
import br.com.fiap.scheduling.interfaces.dtos.AddressDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class AddressDomainToAddressDtoConverter implements Converter<AddressDomain, AddressDto> {

	private final CoordinateToLocationDtoConverter coordinateToLocationDtoConverter;

	public AddressDomainToAddressDtoConverter(final CoordinateToLocationDtoConverter coordinateToLocationDtoConverter) {
		this.coordinateToLocationDtoConverter = coordinateToLocationDtoConverter;
	}

	@Override
	public AddressDto convert(final AddressDomain source) {
		final AddressDto addressDto = new AddressDto();
		source.getCity().ifPresent(addressDto::setCity);
		source.getNeighborhood().ifPresent(addressDto::setNeighborhood);
		source.getNumber().ifPresent(addressDto::setNumber);
		source.getState().ifPresent(addressDto::setState);
		source.getStreet().ifPresent(addressDto::setStreet);
		addressDto.setLocation(coordinateToLocationDtoConverter.convert(source.getCoordinate()));

		return addressDto;
	}
}