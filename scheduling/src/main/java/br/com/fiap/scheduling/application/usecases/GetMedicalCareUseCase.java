package br.com.fiap.scheduling.application.usecases;

import br.com.fiap.scheduling.data.MedicalCareRepository;
import br.com.fiap.scheduling.domain.MedicalCareDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetMedicalCareUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetMedicalCareUseCase.class);

	private final MedicalCareRepository medicalCareRepository;

	public GetMedicalCareUseCase(final  MedicalCareRepository medicalCareRepository) {
		this.medicalCareRepository = medicalCareRepository;
	}

	public MedicalCareDomain find(final String document){
		LOGGER.info("m=find(document={})", document);

		return medicalCareRepository.findById(document) //
				.orElseGet(() -> buildDefaultMedicalCare(document));
	}

	private MedicalCareDomain buildDefaultMedicalCare(final String document) {
		return MedicalCareDomain.builder() //
								.document(document) //
								.build();
	}
}