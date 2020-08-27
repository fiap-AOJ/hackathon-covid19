package br.com.fiap.scheduling.interfaces.converters;

import br.com.fiap.scheduling.domain.Gender;
import br.com.fiap.scheduling.domain.PatientDomain;
import br.com.fiap.scheduling.domain.PersonDomain;
import br.com.fiap.scheduling.interfaces.dtos.PatientDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class PatientDtoToPatientDomainConverter implements Converter<PatientDto, PatientDomain> {

	@Override
	public PatientDomain convert(final PatientDto source) {
		return PatientDomain.builder() //
			.person(buildPerson(source)) //
			.hasRisk(source.getHasRisk()) //
			.isHealthProfessional(source.getHealthProfessional()) //
			.build();
	}

	private PersonDomain.Builder buildPerson(final PatientDto source) {
		return PersonDomain.builderPerson() //
						.name(source.getName()) //
						.document(source.getDocument()) //
						.gender(builderGender(source.getGender())) //
						.birthday(source.getBirthday());
	}

	private Gender builderGender(final String gender) {
		for(Gender genderDomain : Gender.values()){
			if(genderDomain.name().equalsIgnoreCase(gender)){
				return genderDomain;
			}
		}

		throw new RuntimeException(String.format("Failed to find gender by %s", gender));
	}
}
