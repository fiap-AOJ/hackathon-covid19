package br.com.fiap.scheduling.application.usecases;

import br.com.fiap.scheduling.data.PatientRepository;
import br.com.fiap.scheduling.domain.PatientDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetPatientUserCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetPatientUserCase.class);

	private final PatientRepository patientRepository;

	public GetPatientUserCase(final  PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public Optional<PatientDomain> find(final String document){
		try {
			LOGGER.info("m=find(document={})", document);

			return patientRepository.findById(document);
		}catch (Exception exception){
			LOGGER.error("ex(message={}, cause={})", exception.getMessage(), exception);
			return Optional.empty();
		}
	}
}