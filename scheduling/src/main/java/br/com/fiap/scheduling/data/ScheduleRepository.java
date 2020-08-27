package br.com.fiap.scheduling.data;

import br.com.fiap.scheduling.domain.ScheduleDomain;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public interface ScheduleRepository extends MongoRepository<ScheduleDomain, String> {

	public Stream<ScheduleDomain> findByHealthPointIdAndPeriodBeginAfterAndPeriodEndBefore(final String healthPointId, final
			LocalDateTime periodBegin, final LocalDateTime periodEnd);

}