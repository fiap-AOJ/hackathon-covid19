package br.com.fiap.scheduling.application.usecases;

import br.com.fiap.scheduling.data.BookHealthServiceRepository;
import br.com.fiap.scheduling.data.MedicalCareRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PatientVaccinatedUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(PatientVaccinatedUseCase.class);

	private final MedicalCareRepository medicalCareRepository;
	private final BookHealthServiceRepository bookHealthServiceRepository;

	public PatientVaccinatedUseCase(final MedicalCareRepository medicalCareRepository,
			final BookHealthServiceRepository bookHealthServiceRepository) {
		this.medicalCareRepository = medicalCareRepository;
		this.bookHealthServiceRepository = bookHealthServiceRepository;
	}

	public void vaccinated(final String document){
		LOGGER.info("m=vaccinated(document={})", document);

		medicalCareRepository.findById(document) //
			.map(medicalCareDomain -> {
				medicalCareDomain.vaccinated();
				return medicalCareDomain;
			}).map(medicalCareRepository::save);

		bookHealthServiceRepository.deleteById(document);
	}
}