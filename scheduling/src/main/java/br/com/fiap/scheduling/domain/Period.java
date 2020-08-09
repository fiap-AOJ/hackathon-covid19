package br.com.fiap.scheduling.domain;

import java.time.LocalDateTime;

public class Period {

	private final LocalDateTime begin;

	private final LocalDateTime end;

	private Period(final LocalDateTime begin, final LocalDateTime end){
		this.begin = begin;
		this.end = end;
	}

	public static final Period of(final LocalDateTime begin, final LocalDateTime end){
		return new Period(begin, end);
	}

	public LocalDateTime getBegin() {
		return begin;
	}

	public LocalDateTime getEnd() {
		return end;
	}
}
