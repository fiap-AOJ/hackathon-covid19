package br.com.fiap.scheduling.interfaces.converters;

import br.com.fiap.scheduling.domain.HealthPointDomain;
import br.com.fiap.scheduling.domain.Period;
import br.com.fiap.scheduling.domain.ScheduleDomain;
import br.com.fiap.scheduling.interfaces.dtos.PeriodDto;
import br.com.fiap.scheduling.interfaces.dtos.ScheduleDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Lazy
@Component
public class ScheduleDtoToScheduleDomainConverter implements
		Converter<Map.Entry<HealthPointDomain, ScheduleDto>, ScheduleDomain> {

	@Override
	public ScheduleDomain convert(Map.Entry<HealthPointDomain, ScheduleDto> source) {
		final HealthPointDomain healthPointDomain = source.getKey();
		final ScheduleDto scheduleDto = source.getValue();
		return ScheduleDomain.builder() //
					.healthPoint(healthPointDomain) //
					.period(buildPeriod(scheduleDto)) //
					.capacity(scheduleDto.getCapacity()) //
					.build();
	}

	private Period buildPeriod(final ScheduleDto scheduleDto) {
		final PeriodDto periodDto = scheduleDto.getPeriod();
		return Period.of(periodDto.getBegin(), periodDto.getEnd());
	}
}