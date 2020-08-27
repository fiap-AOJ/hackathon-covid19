package br.com.fiap.scheduling.interfaces.converters;

import br.com.fiap.scheduling.domain.MedicalCareDomain;
import br.com.fiap.scheduling.domain.PatientDomain;
import br.com.fiap.scheduling.interfaces.dtos.MedicalCareDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Lazy
@Component
public class EntryOfPatientByMedicalCareToMedicalCareDtoConverter implements Converter<Map.Entry<PatientDomain, MedicalCareDomain>, MedicalCareDto> {

	private final PatientDomainToPatientDtoConverter patientDomainToPatientDtoConverter;
	private final PatientStateToPatienteDtoConverter patientStateToPatienteDtoConverter;

	public EntryOfPatientByMedicalCareToMedicalCareDtoConverter(
			final PatientDomainToPatientDtoConverter patientDomainToPatientDtoConverter,
			final PatientStateToPatienteDtoConverter patientStateToPatienteDtoConverter) {
		this.patientDomainToPatientDtoConverter = patientDomainToPatientDtoConverter;
		this.patientStateToPatienteDtoConverter = patientStateToPatienteDtoConverter;
	}

	@Override
	public MedicalCareDto convert(final Map.Entry<PatientDomain, MedicalCareDomain> source) {
		final PatientDomain patientDomain = source.getKey();
		final MedicalCareDomain medicalCareDomain = source.getValue();
		final MedicalCareDto medicalCareDto = new MedicalCareDto();
		medicalCareDto.setPatient(patientDomainToPatientDtoConverter.convert(patientDomain));
		medicalCareDto.setState(patientStateToPatienteDtoConverter.convert(medicalCareDomain.getSituation()));

		return medicalCareDto;
	}
}