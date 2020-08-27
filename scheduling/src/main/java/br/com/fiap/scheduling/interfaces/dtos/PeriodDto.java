package br.com.fiap.scheduling.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PeriodDto {

	@NotNull(message = "Campo obrigatório")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime begin;

	@NotNull(message = "Campo obrigatório")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime end;

	public LocalDateTime getEnd() {
		return end;
	}

	public LocalDateTime getBegin() {
		return begin;
	}

	public void setBegin(LocalDateTime begin) {
		this.begin = begin;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
}
