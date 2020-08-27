package br.com.fiap.scheduling.application.usecases;

import static br.com.fiap.scheduling.domain.Situation.REGISTER_PENDING;

import br.com.fiap.scheduling.data.MedicalCareRepository;
import br.com.fiap.scheduling.domain.MedicalCareDomain;
import br.com.fiap.scheduling.domain.Situation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetDocumentSituationUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetDocumentSituationUseCase.class);

	private final MedicalCareRepository medicalCareRepository;

	public GetDocumentSituationUseCase(final MedicalCareRepository medicalCareRepository) {
		this.medicalCareRepository = medicalCareRepository;
	}

	public Situation find(final String document){
		try {
			LOGGER.info("m=find(document={})", document);

			return medicalCareRepository.findById(document) //
				.map(MedicalCareDomain::getSituation) //
				.orElseGet(() -> REGISTER_PENDING);
		}catch (Exception exception){
			LOGGER.error("ex(message={}, cause={})", exception.getMessage(), exception);
			return REGISTER_PENDING;
		}
	}
}