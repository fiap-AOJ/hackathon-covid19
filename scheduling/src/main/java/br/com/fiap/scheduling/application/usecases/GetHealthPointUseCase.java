package br.com.fiap.scheduling.application.usecases;

import br.com.fiap.scheduling.data.HealthPointRepository;
import br.com.fiap.scheduling.domain.Coordinate;
import br.com.fiap.scheduling.domain.HealthPointDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GetHealthPointUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetHealthPointUseCase.class);

	private final HealthPointRepository healthPointRepository;

	public GetHealthPointUseCase(HealthPointRepository healthPointRepository) {
		this.healthPointRepository = healthPointRepository;
	}

	public Optional<HealthPointDomain> find(final String id){
		try {
			LOGGER.info("m=find(id={})", id);

			return healthPointRepository.findById(id);
		}catch (Exception exception){
			LOGGER.error("ex(message={}, cause={})", exception.getMessage(), exception);
			return Optional.empty();
		}
	}

	//TODO: Recuperar baseado na coordenada
	public Set<HealthPointDomain> find(final Coordinate coordinate){
		try {
			LOGGER.info("m=find(coordinate={})", coordinate);

			return healthPointRepository.findAll().stream().collect(Collectors.toSet());
		}catch (Exception exception){
			LOGGER.error("ex(message={}, cause={})", exception.getMessage(), exception);
			return Collections.emptySet();
		}
	}
}