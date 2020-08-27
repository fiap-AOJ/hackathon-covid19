package br.com.fiap.scheduling.interfaces.converters;

import br.com.fiap.scheduling.domain.PatientDomain;
import br.com.fiap.scheduling.interfaces.dtos.PatientDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Lazy
@Component
class PatientDomainToPatientDtoConverter implements Converter<PatientDomain, PatientDto> {

	@Override
	public PatientDto convert(final PatientDomain source) {
		final PatientDto patientDto = new PatientDto();
		patientDto.setDocument(source.getDocument());
		patientDto.setHasRisk(source.hasRisk());
		patientDto.setHealthProfessional(source.isHealthProfessional());
		patientDto.setName(source.getName());
		source.getBirthday().ifPresent(patientDto::setBirthday);
		source.getGender().ifPresent(gender -> patientDto.setGender(gender.name()));

		return patientDto;
	}
}
