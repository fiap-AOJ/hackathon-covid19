package br.com.fiap.scheduling.application.usecases;

import br.com.fiap.scheduling.data.BookHealthServiceRepository;
import br.com.fiap.scheduling.data.MedicalCareRepository;
import br.com.fiap.scheduling.domain.BookHealthServiceDomain;
import br.com.fiap.scheduling.domain.PatientDomain;
import br.com.fiap.scheduling.domain.ScheduleDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ScheduleReserveUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleReserveUseCase.class);

	private final BookHealthServiceRepository bookHealthServiceRepository;
	private final MedicalCareRepository medicalCareRepository;

	public ScheduleReserveUseCase(final BookHealthServiceRepository bookHealthServiceRepository,
			final MedicalCareRepository medicalCareRepository) {
		this.bookHealthServiceRepository = bookHealthServiceRepository;
		this.medicalCareRepository = medicalCareRepository;
	}

	public void reserve(final PatientDomain patientDomain, final ScheduleDomain scheduleDomain) {
		LOGGER.info("m=reserve(patientDomain={}, scheduleDomain={})", patientDomain, scheduleDomain);

		final BookHealthServiceDomain bookHealthServiceDomain = buildBookHealthService(patientDomain, scheduleDomain);
		bookHealthServiceRepository.save(bookHealthServiceDomain);
		medicalCareRepository.findById(patientDomain.getDocument()) //
				.map(medicalCareDomain -> {
					medicalCareDomain.pendingVacc();
					return medicalCareDomain;
				}).map(medicalCareRepository::save);
	}

	private BookHealthServiceDomain buildBookHealthService(final PatientDomain patientDomain, final ScheduleDomain scheduleDomain) {
		return BookHealthServiceDomain.builder() //
					.document(patientDomain.getDocument()) //
					.scheduleId(scheduleDomain.getId()) //
					.build();
	}
}