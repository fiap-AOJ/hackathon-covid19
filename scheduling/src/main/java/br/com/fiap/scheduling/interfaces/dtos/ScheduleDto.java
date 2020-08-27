package br.com.fiap.scheduling.interfaces.dtos;

import br.com.fiap.scheduling.domain.ScheduleDomain;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

public class ScheduleDto {

	@JsonProperty(access = READ_ONLY)
	private String id;

	@Valid
	@NotNull(message = "Campo obrigatório")
	private PeriodDto period;

	@Min(value = 1, message = "O campo deve ter um valor igual ou maior que {min}")
	@JsonProperty(access = WRITE_ONLY)
	private Integer capacity = 1;

	public String getId() {
		return id;
	}

	public PeriodDto getPeriod() {
		return period;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPeriod(PeriodDto period) {
		this.period = period;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
}
