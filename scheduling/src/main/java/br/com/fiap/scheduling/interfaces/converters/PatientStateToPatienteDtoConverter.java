package br.com.fiap.scheduling.interfaces.converters;

import br.com.fiap.scheduling.domain.Situation;
import br.com.fiap.scheduling.interfaces.dtos.PatientSituationDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class PatientStateToPatienteDtoConverter implements Converter<Situation, PatientSituationDto> {

	@Override
	public PatientSituationDto convert(final Situation source) {
		final PatientSituationDto patientSituationDto = new PatientSituationDto();
		patientSituationDto.setState(source.name().toLowerCase());
		patientSituationDto.setDescription(source.getDescription());

		return patientSituationDto;
	}
}
