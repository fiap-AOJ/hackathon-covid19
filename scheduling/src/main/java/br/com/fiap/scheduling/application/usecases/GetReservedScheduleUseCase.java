package br.com.fiap.scheduling.application.usecases;

import br.com.fiap.scheduling.data.BookHealthServiceRepository;
import br.com.fiap.scheduling.data.ScheduleRepository;
import br.com.fiap.scheduling.domain.BookHealthServiceDomain;
import br.com.fiap.scheduling.domain.ScheduleDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetReservedScheduleUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetReservedScheduleUseCase.class);

	private final BookHealthServiceRepository bookHealthServiceRepository;
	private final ScheduleRepository scheduleRepository;

	public GetReservedScheduleUseCase(final BookHealthServiceRepository bookHealthServiceRepository,
			final ScheduleRepository scheduleRepository) {
		this.bookHealthServiceRepository = bookHealthServiceRepository;
		this.scheduleRepository = scheduleRepository;
	}

	public Optional<ScheduleDomain> find(final String document){
		LOGGER.info("m=find(document={})", document);

		return bookHealthServiceRepository.findById(document) //
				.map(BookHealthServiceDomain::getScheduleId) //
				.map(scheduleRepository::findById) //
				.orElseGet(Optional::empty);
	}
}