package br.com.fiap.scheduling.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document
public class BookHealthServiceDomain {

	@MongoId
	private String document;

	private String scheduleId;

	private LocalDateTime createdAt;

	private BookHealthServiceDomain(final Builder builder){
		this.document = builder.document;
		this.scheduleId = builder.scheduleId;
		this.createdAt = builder.createdAt;
	}

	public String getDocument() {
		return document;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public LocalDateTime createdAt() {
		return createdAt;
	}

	public BookHealthServiceDomain(){}

	public static final Document builder(){
		return new Builder();
	}

	public static final class Builder implements Document, ScheduleId, Build{
		private String document;
		private String scheduleId;
		private LocalDateTime createdAt = LocalDateTime.now();

		@Override
		public ScheduleId document(final String document) {
			this.document = document;
			return this;
		}

		@Override
		public Build scheduleId(final String scheduleId) {
			this.scheduleId = scheduleId;
			return this;
		}

		@Override
		public BookHealthServiceDomain build() {
			return new BookHealthServiceDomain(this);
		}
	}

	public interface Document{
		public ScheduleId document(final String document);
	}

	public interface ScheduleId{
		public Build scheduleId(final String scheduleId);
	}

	public interface Build{
		public BookHealthServiceDomain build();
	}
}