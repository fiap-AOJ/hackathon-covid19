package br.com.fiap.scheduling.application.usecases;

import br.com.fiap.scheduling.data.PatientRepository;
import br.com.fiap.scheduling.domain.PatientDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterPatientUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterPatientUseCase.class);

	private final PatientRepository patientRepository;
	private final RegisterMedicalCareUseCase registerMedicalCareUseCase;

	public RegisterPatientUseCase(final PatientRepository patientRepository, final RegisterMedicalCareUseCase registerMedicalCareUseCase) {
		this.patientRepository = patientRepository;
		this.registerMedicalCareUseCase = registerMedicalCareUseCase;
	}

	public Optional<PatientDomain> register(final PatientDomain patientDomain){
		try{
			LOGGER.info("m=register(patientDomain={})", patientDomain);

			registerMedicalCareUseCase.register(patientDomain.getDocument());
			patientRepository.save(patientDomain);
			return Optional.of(patientDomain);
		}catch (Exception exception){
			LOGGER.error("ex(message={}, cause{})", exception.getMessage(), exception);
			return Optional.empty();
		}
	}
}