package br.com.fiap.scheduling.application.usecases;

import br.com.fiap.scheduling.data.ScheduleRepository;
import br.com.fiap.scheduling.domain.HealthPointDomain;
import br.com.fiap.scheduling.domain.Period;
import br.com.fiap.scheduling.domain.ScheduleDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class GetScheduleUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetScheduleUseCase.class);

	private final ScheduleRepository scheduleRepository;

	public GetScheduleUseCase(final ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}

	public Stream<ScheduleDomain> find(final HealthPointDomain healthPointDomain, final Period period){
		LOGGER.debug("m=find(healthPointDomain={}, period={})", healthPointDomain, period);

		return scheduleRepository.findByHealthPointIdAndPeriodBeginAfterAndPeriodEndBefore(healthPointDomain.getId(), period.getBegin(), period.getEnd());
	}

	public Optional<ScheduleDomain> find(final String id){
		LOGGER.debug("m=find(id={})", id);

		return scheduleRepository.findById(id);
	}
}