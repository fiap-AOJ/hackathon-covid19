package br.com.fiap.scheduling.data;

import br.com.fiap.scheduling.domain.HealthPointDomain;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HealthPointRepository extends MongoRepository<HealthPointDomain, String> { }