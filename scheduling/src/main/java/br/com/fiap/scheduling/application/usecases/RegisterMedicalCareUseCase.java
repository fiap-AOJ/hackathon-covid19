package br.com.fiap.scheduling.application.usecases;

import br.com.fiap.scheduling.data.MedicalCareRepository;
import br.com.fiap.scheduling.domain.MedicalCareDomain;
import br.com.fiap.scheduling.domain.Situation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegisterMedicalCareUseCase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterMedicalCareUseCase.class);
	
	private final MedicalCareRepository medicalCareRepository;

	public RegisterMedicalCareUseCase(final MedicalCareRepository medicalCareRepository) {
		this.medicalCareRepository = medicalCareRepository;
	}

	public void register(final String document){
		LOGGER.info("m=register(document={})", document);
		
		medicalCareRepository.save(buildMedicalCare(document));
	}

	private MedicalCareDomain buildMedicalCare(final String document) {
		return MedicalCareDomain.builder() //
								.document(document) //
								.situation(Situation.PENDING_SCHEDULING) //
								.build();
	}

}
