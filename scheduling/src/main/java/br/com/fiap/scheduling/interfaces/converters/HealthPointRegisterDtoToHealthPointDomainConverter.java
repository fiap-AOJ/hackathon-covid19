package br.com.fiap.scheduling.interfaces.converters;

import br.com.fiap.scheduling.domain.HealthPointDomain;
import br.com.fiap.scheduling.interfaces.dtos.AddressDto;
import br.com.fiap.scheduling.interfaces.dtos.HealthPointDto;
import br.com.fiap.scheduling.interfaces.dtos.HealthPointRegisterDto;
import br.com.fiap.scheduling.interfaces.dtos.LocationDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Lazy
@Component
public class HealthPointRegisterDtoToHealthPointDomainConverter implements Converter<HealthPointRegisterDto, HealthPointDomain> {

	private final AddressDtoToAddressDomainConverter addressDtoToAddressDomainConverter;

	public HealthPointRegisterDtoToHealthPointDomainConverter(
			final AddressDtoToAddressDomainConverter addressDtoToAddressDomainConverter) {
		this.addressDtoToAddressDomainConverter = addressDtoToAddressDomainConverter;
	}

	@Override
	public HealthPointDomain convert(final HealthPointRegisterDto source) {
		final HealthPointDto healthPointDto = source.getHealthPoint();
		defineAddress(healthPointDto, source.getLocation());

		return HealthPointDomain.builder() //
			.name(healthPointDto.getName()) //
			.address(addressDtoToAddressDomainConverter.convert(healthPointDto.getAddress())) //
			.build();
	}

	private void defineAddress(final HealthPointDto healthPointDto, final LocationDto location) {
		if(isNull(healthPointDto.getAddress())){
			final AddressDto addressDto = new AddressDto();
			addressDto.setLocation(location);
			healthPointDto.setAddress(addressDto);
			return;
		}

		healthPointDto.getAddress().setLocation(location);
	}
}
