package br.com.fiap.scheduling.application.usecases;

import br.com.fiap.scheduling.data.ScheduleRepository;
import br.com.fiap.scheduling.domain.ScheduleDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RegisterScheduleUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterScheduleUseCase.class);

	private final ScheduleRepository scheduleRepository;

	public RegisterScheduleUseCase(final ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}

	public void register(final Set<ScheduleDomain> schedules){
		LOGGER.debug("m=register(schedulesSize={})", schedules.size());

		scheduleRepository.saveAll(schedules);
	}

	public void register(final ScheduleDomain schedule){
		LOGGER.debug("m=register(schedule={})", schedule);

		scheduleRepository.save(schedule);
	}
}