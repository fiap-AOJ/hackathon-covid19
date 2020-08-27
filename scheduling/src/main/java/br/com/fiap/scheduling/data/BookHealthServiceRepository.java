package br.com.fiap.scheduling.data;

import br.com.fiap.scheduling.domain.BookHealthServiceDomain;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookHealthServiceRepository extends MongoRepository<BookHealthServiceDomain,String> { }