package br.com.fiap.scheduling.data;

import br.com.fiap.scheduling.domain.MedicalCareDomain;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicalCareRepository extends MongoRepository<MedicalCareDomain, String> { }