package br.com.fiap.scheduling.application.usecases;

import br.com.fiap.scheduling.data.HealthPointRepository;
import br.com.fiap.scheduling.domain.HealthPointDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegisterHealthPointUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterPatientUseCase.class);

	private final HealthPointRepository healthPointRepository;

	public RegisterHealthPointUseCase(final HealthPointRepository healthPointRepository) {
		this.healthPointRepository = healthPointRepository;
	}

	public HealthPointDomain register(final HealthPointDomain healthPointDomain){
		LOGGER.info("m=register(healthPointDomain={})", healthPointDomain);

		return healthPointRepository.save(healthPointDomain);
	}
}