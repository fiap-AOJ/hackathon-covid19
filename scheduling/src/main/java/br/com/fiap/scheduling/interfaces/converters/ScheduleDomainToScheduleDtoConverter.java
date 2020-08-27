package br.com.fiap.scheduling.interfaces.converters;

import br.com.fiap.scheduling.domain.Period;
import br.com.fiap.scheduling.domain.ScheduleDomain;
import br.com.fiap.scheduling.interfaces.dtos.PeriodDto;
import br.com.fiap.scheduling.interfaces.dtos.ScheduleDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class ScheduleDomainToScheduleDtoConverter implements Converter<ScheduleDomain, ScheduleDto> {

	@Override
	public ScheduleDto convert(ScheduleDomain source) {
		final ScheduleDto scheduleDto = new ScheduleDto();
		scheduleDto.setId(source.getId());
		scheduleDto.setPeriod(buildPeriond(source));

		return scheduleDto;
	}

	private PeriodDto buildPeriond(ScheduleDomain source) {
		final Period period = source.getPeriod();
		final PeriodDto periodDto = new PeriodDto();
		periodDto.setBegin(period.getBegin());
		periodDto.setEnd(period.getEnd());

		return periodDto;
	}
}
