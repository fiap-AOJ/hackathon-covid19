package br.com.fiap.scheduling.interfaces;

import br.com.fiap.scheduling.application.usecases.*;
import br.com.fiap.scheduling.domain.*;
import br.com.fiap.scheduling.interfaces.converters.*;
import br.com.fiap.scheduling.interfaces.dtos.HealthPointDto;
import br.com.fiap.scheduling.interfaces.dtos.HealthPointRegisterDto;
import br.com.fiap.scheduling.interfaces.dtos.ScheduleDto;
import br.com.fiap.scheduling.interfaces.dtos.ScheduleWithHealthPointDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "${api.version.v1}/healthpoints")
public class HealthPointController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HealthPointController.class);

	private final RegisterHealthPointUseCase registerHealthPointUseCase;
	private final HealthPointDomainToHealthPointDtoConverter healthPointDomainToHealthPointDtoConverter;
	private final HealthPointRegisterDtoToHealthPointDomainConverter healthPointRegisterDtoToHealthPointDomainConverter;
	private final ScheduleDtoToScheduleDomainConverter scheduleDtoToScheduleDomainConverter;
	private final GetHealthPointUseCase getHealthPointUseCase;
	private final RegisterScheduleUseCase registerScheduleUseCase;
	private final GetScheduleUseCase getScheduleUseCase;
	private final ScheduleDomainToScheduleDtoConverter scheduleDomainToScheduleDtoConverter;
	private final GetPatientUserCase getPatientUserCase;
	private final ScheduleReserveUseCase scheduleReserveUseCase;
	private final GetReservedScheduleUseCase getReservedScheduleUseCase;
	private final ScheduleDomainToScheduleWithHealthPointDtoConverter scheduleDomainToScheduleWithHealthPointDtoConverter;

	public HealthPointController(final RegisterHealthPointUseCase registerHealthPointUseCase,
			final HealthPointDomainToHealthPointDtoConverter healthPointDomainToHealthPointDtoConverter,
			final HealthPointRegisterDtoToHealthPointDomainConverter healthPointRegisterDtoToHealthPointDomainConverter,
			final ScheduleDtoToScheduleDomainConverter scheduleDtoToScheduleDomainConverter,
			final GetHealthPointUseCase getHealthPointUseCase, final RegisterScheduleUseCase registerScheduleUseCase,
			final GetScheduleUseCase getScheduleUseCase,
			final ScheduleDomainToScheduleDtoConverter scheduleDomainToScheduleDtoConverter,
			final GetPatientUserCase getPatientUserCase, final ScheduleReserveUseCase scheduleReserveUseCase,
			final GetReservedScheduleUseCase getReservedScheduleUseCase,
			final ScheduleDomainToScheduleWithHealthPointDtoConverter scheduleDomainToScheduleWithHealthPointDtoConverter) {
		this.registerHealthPointUseCase = registerHealthPointUseCase;
		this.healthPointDomainToHealthPointDtoConverter = healthPointDomainToHealthPointDtoConverter;
		this.healthPointRegisterDtoToHealthPointDomainConverter = healthPointRegisterDtoToHealthPointDomainConverter;
		this.scheduleDtoToScheduleDomainConverter = scheduleDtoToScheduleDomainConverter;
		this.getHealthPointUseCase = getHealthPointUseCase;
		this.registerScheduleUseCase = registerScheduleUseCase;
		this.getScheduleUseCase = getScheduleUseCase;
		this.scheduleDomainToScheduleDtoConverter = scheduleDomainToScheduleDtoConverter;
		this.getPatientUserCase = getPatientUserCase;
		this.scheduleReserveUseCase = scheduleReserveUseCase;
		this.getReservedScheduleUseCase = getReservedScheduleUseCase;
		this.scheduleDomainToScheduleWithHealthPointDtoConverter = scheduleDomainToScheduleWithHealthPointDtoConverter;
	}

	@GetMapping
	@ResponseStatus(OK)
	public Flux<HealthPointDto> find(@RequestParam("latitude") final Double latitude, @RequestParam("longitude") final Double longitude){
		LOGGER.debug("m=find(latitude={}, longitude={})", latitude, longitude);

		final Coordinate coordinate = Coordinate.of(latitude, longitude);
		return Flux.fromStream(getHealthPointUseCase.find(coordinate)
				.stream() //
				.map(healthPointDomainToHealthPointDtoConverter::convert));
	}


	@PostMapping
	@ResponseStatus(CREATED)
	public Mono<HealthPointDto> register(@RequestBody @Valid HealthPointRegisterDto registerDto){
		LOGGER.debug("m=register(registerDto={})", registerDto);

		final HealthPointDomain healthPointDomain = healthPointRegisterDtoToHealthPointDomainConverter.convert(registerDto);
		return Optional.of(registerHealthPointUseCase.register(healthPointDomain))
				.map(healthPointDomainToHealthPointDtoConverter::convert) //
				.map(Mono::just) //
				.orElseGet(() -> Mono.error(new IllegalArgumentException()));
	}


	@PostMapping("{id}/schedules")
	@ResponseStatus(CREATED)
	public Mono<Void> register(@PathVariable("id") final String id, @RequestBody @Valid Set<ScheduleDto> schedulesDto){
		LOGGER.debug("m=register(schedulesDtoSize={})", schedulesDto.size());

		final HealthPointDomain healthPointDomain = getHealthPointUseCase.find(id).orElseThrow(() -> new IllegalArgumentException());
		final Set<ScheduleDomain> schedulesDomain  = schedulesDto //
				.stream() //
				.map(scheduleDto -> convert(healthPointDomain, scheduleDto)) //
				.collect(Collectors.toSet());
		registerScheduleUseCase.register(schedulesDomain);

		return Mono.empty();
	}

	private ScheduleDomain convert(final HealthPointDomain healthPointDomain, final ScheduleDto scheduleDto){
		return scheduleDtoToScheduleDomainConverter.convert(new AbstractMap.SimpleEntry<>(healthPointDomain, scheduleDto));
	}

	@GetMapping("{id}/schedules")
	@ResponseStatus(OK)
	public Flux<ScheduleDto> find(@PathVariable("id") final String id,
			@RequestParam(name = "endPeriod", required = false)
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endPeriod){
		LOGGER.debug("m=find(id={})", id);

		final Period period = buildPeriod(endPeriod);
		final HealthPointDomain healthPointDomain = getHealthPointUseCase.find(id).orElseThrow(() -> new IllegalArgumentException());

		return Flux.fromStream(getScheduleUseCase.find(healthPointDomain, period) //
				.map(scheduleDomainToScheduleDtoConverter::convert));
	}

	@PostMapping("/schedules/{scheduleId}")
	@ResponseStatus(CREATED)
	public Mono<Void> book(
			@PathVariable("scheduleId") final String scheduleId,
			@RequestParam("document") final String document){
		LOGGER.debug("m=book(scheduleId={}, document={})", scheduleId, document);

		final PatientDomain patientDomain = getPatientUserCase.find(document).orElseThrow(() -> new IllegalArgumentException());
		final ScheduleDomain scheduleDomain = getScheduleUseCase.find(scheduleId).orElseThrow(() -> new IllegalArgumentException());
		scheduleReserveUseCase.reserve(patientDomain, scheduleDomain);

		return Mono.empty();
	}

	@GetMapping("/schedules/reserved")
	@ResponseStatus(CREATED)
	public Mono<ScheduleWithHealthPointDto> getReservedSchedule(@RequestParam("document") final String document){
		LOGGER.debug("m=getReservedSchedule(document={})", document);

		return getReservedScheduleUseCase.find(document) //
					.map(scheduleDomainToScheduleWithHealthPointDtoConverter::convert) //
					.map(Mono::just) //
					.orElseGet(() -> Mono.error(new IllegalArgumentException()));
	}

	private Period buildPeriod(final LocalDate endPeriod) {
		final LocalDateTime startPeriod = LocalDateTime.now();
		LocalDateTime endTimePeriod = endPeriod.atStartOfDay();
		if(isNull(endPeriod) || endTimePeriod.isBefore(startPeriod)){
			endTimePeriod = startPeriod.plusDays(1l);
		}

		return Period.of(startPeriod.minusDays(1l), endTimePeriod.plusDays(1l));
	}
}