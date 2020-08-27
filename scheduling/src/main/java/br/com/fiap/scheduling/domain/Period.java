package br.com.fiap.scheduling.domain;

import java.time.LocalDateTime;

public class Period {

	private LocalDateTime begin;

	private LocalDateTime end;

	private Period(final LocalDateTime begin, final LocalDateTime end){
		this.begin = begin;
		this.end = end;
	}

	public LocalDateTime getBegin() {
		return begin;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public Period(){}

	public static final Period of(final LocalDateTime begin, final LocalDateTime end){
		return new Period(begin, end);
	}
}
