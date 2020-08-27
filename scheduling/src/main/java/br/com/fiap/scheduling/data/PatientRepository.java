package br.com.fiap.scheduling.data;

import br.com.fiap.scheduling.domain.PatientDomain;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<PatientDomain, String> { }