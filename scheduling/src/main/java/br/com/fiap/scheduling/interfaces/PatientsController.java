package br.com.fiap.scheduling.interfaces;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;

import br.com.fiap.scheduling.application.usecases.*;
import br.com.fiap.scheduling.domain.MedicalCareDomain;
import br.com.fiap.scheduling.domain.PatientDomain;
import br.com.fiap.scheduling.domain.Situation;
import br.com.fiap.scheduling.interfaces.converters.PatientDtoToPatientDomainConverter;
import br.com.fiap.scheduling.interfaces.converters.EntryOfPatientByMedicalCareToMedicalCareDtoConverter;
import br.com.fiap.scheduling.interfaces.dtos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path = "${api.version.v1}/patients")
public class PatientsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PatientsController.class);

	private final GetDocumentSituationUseCase getDocumentSituationUseCase;
	private final Converter<Situation, PatientSituationDto> patientStateToPatienteDtoConverter;
	private final RegisterPatientUseCase registerPatientUseCase;
	private final PatientDtoToPatientDomainConverter patientDtoToPatientDomainConverter;
	private final EntryOfPatientByMedicalCareToMedicalCareDtoConverter entryOfPatientByMedicalCareToMedicalCareDtoConverter;
	private final GetMedicalCareUseCase getMedicalCareUseCase;
	private final PatientVaccinatedUseCase patientVaccinatedUseCase;
	private final GetPatientUserCase getPatientUserCase;

	public PatientsController(final GetDocumentSituationUseCase getDocumentSituationUseCase,
			final Converter<Situation, PatientSituationDto> patientStateToPatienteDtoConverter,
			final RegisterPatientUseCase registerPatientUseCase,
			final PatientDtoToPatientDomainConverter patientDtoToPatientDomainConverter,
			final EntryOfPatientByMedicalCareToMedicalCareDtoConverter entryOfPatientByMedicalCareToMedicalCareDtoConverter,
			final GetMedicalCareUseCase getMedicalCareUseCase, final PatientVaccinatedUseCase patientVaccinatedUseCase,
			final GetPatientUserCase getPatientUserCase) {
		this.getDocumentSituationUseCase = getDocumentSituationUseCase;
		this.patientStateToPatienteDtoConverter = patientStateToPatienteDtoConverter;
		this.registerPatientUseCase = registerPatientUseCase;
		this.patientDtoToPatientDomainConverter = patientDtoToPatientDomainConverter;
		this.entryOfPatientByMedicalCareToMedicalCareDtoConverter = entryOfPatientByMedicalCareToMedicalCareDtoConverter;
		this.getMedicalCareUseCase = getMedicalCareUseCase;
		this.patientVaccinatedUseCase = patientVaccinatedUseCase;
		this.getPatientUserCase = getPatientUserCase;
	}

	@GetMapping("{document}/state")
	@ResponseStatus(OK)
	public Mono<PatientSituationDto> getPatientSituation(@PathVariable("document") final String document){
		LOGGER.debug("m=getPatientSituation(document={})", document);

		return Optional.of(getDocumentSituationUseCase.find(document)) //
				.map(patientStateToPatienteDtoConverter::convert) //
				.map(Mono::just) //
				.orElseGet(() -> Mono.error(new IllegalArgumentException()));
	}

	@GetMapping("{document}")
	@ResponseStatus(OK)
	public Mono<MedicalCareDto> getPatient(@PathVariable("document") final String document){
		LOGGER.debug("m=getPatient(document={})", document);

		return getPatientUserCase.find(document) //
					.map(patientDomain -> buildEntryOfPatientByMedicalCare(patientDomain, getMedicalCareUseCase.find(patientDomain.getDocument()))) //
					.map(entryOfPatientByMedicalCareToMedicalCareDtoConverter::convert) //
					.map(Mono::just) //
					.orElseGet(() -> Mono.error(new IllegalArgumentException()));
	}

	private Map.Entry<PatientDomain, MedicalCareDomain> buildEntryOfPatientByMedicalCare(final PatientDomain patientDomain, final MedicalCareDomain medicalCareDomain) {
		return new AbstractMap.SimpleEntry<>(patientDomain, medicalCareDomain);
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public Mono<MedicalCareDto> register(@RequestBody @Valid RegisterPatientDto registerDto){
		LOGGER.debug("m=register(registerDto={})", registerDto);

		final PatientDto patientDto = registerDto.getPatient();
		return registerPatientUseCase.register(patientDtoToPatientDomainConverter.convert(patientDto)) //
				.map(patientDomain -> buildEntryOfPatientByMedicalCare(patientDomain, getMedicalCareUseCase.find(patientDomain.getDocument()))) //
				.map(entryOfPatientByMedicalCareToMedicalCareDtoConverter::convert) //
				.map(Mono::just) //
				.orElseGet(() -> Mono.error(new RuntimeException()));
	}

	@PutMapping("{document}/vaccinated")
	@ResponseStatus(OK)
	public Mono<Void> vaccinated(@PathVariable("document") final String document){
		LOGGER.debug("m=vaccinated(document={})", document);

		patientVaccinatedUseCase.vaccinated(document);
		return Mono.empty();
	}
}