package br.com.fiap.scheduling.interfaces.converters;

import br.com.fiap.scheduling.domain.HealthPointDomain;
import br.com.fiap.scheduling.interfaces.dtos.HealthPointDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class HealthPointDomainToHealthPointDtoConverter implements Converter<HealthPointDomain, HealthPointDto> {

	private final AddressDomainToAddressDtoConverter addressDomainToAddressDtoConverter;

	public HealthPointDomainToHealthPointDtoConverter(
			final AddressDomainToAddressDtoConverter addressDomainToAddressDtoConverter) {
		this.addressDomainToAddressDtoConverter = addressDomainToAddressDtoConverter;
	}

	@Override
	public HealthPointDto convert(final HealthPointDomain source) {
		final HealthPointDto healthPointDto = new HealthPointDto();
		healthPointDto.setId(source.getId());
		healthPointDto.setName(source.getName());
		healthPointDto.setAddress(addressDomainToAddressDtoConverter.convert(source.getAddress()));

		return healthPointDto;
	}
}