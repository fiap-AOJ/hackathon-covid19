package br.com.fiap.scheduling.interfaces.converters;

import br.com.fiap.scheduling.domain.Period;
import br.com.fiap.scheduling.domain.ScheduleDomain;
import br.com.fiap.scheduling.interfaces.dtos.HealthPointDto;
import br.com.fiap.scheduling.interfaces.dtos.PeriodDto;
import br.com.fiap.scheduling.interfaces.dtos.ScheduleWithHealthPointDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class ScheduleDomainToScheduleWithHealthPointDtoConverter implements Converter<ScheduleDomain, ScheduleWithHealthPointDto> {

	private final HealthPointDomainToHealthPointDtoConverter healthPointDomainToHealthPointDtoConverter;

	public ScheduleDomainToScheduleWithHealthPointDtoConverter(
			final HealthPointDomainToHealthPointDtoConverter healthPointDomainToHealthPointDtoConverter) {
		this.healthPointDomainToHealthPointDtoConverter = healthPointDomainToHealthPointDtoConverter;
	}

	@Override
	public ScheduleWithHealthPointDto convert(ScheduleDomain source) {
		final ScheduleWithHealthPointDto scheduleDto = new ScheduleWithHealthPointDto();
		scheduleDto.setId(source.getId());
		scheduleDto.setPeriod(buildPeriond(source));
		scheduleDto.setHealthPoint(buildHealthPointDto(source));

		return scheduleDto;
	}

	private HealthPointDto buildHealthPointDto(final ScheduleDomain source) {
		return healthPointDomainToHealthPointDtoConverter.convert(source.getHealthPoint());
	}

	private PeriodDto buildPeriond(ScheduleDomain source) {
		final Period period = source.getPeriod();
		final PeriodDto periodDto = new PeriodDto();
		periodDto.setBegin(period.getBegin());
		periodDto.setEnd(period.getEnd());

		return periodDto;
	}
}