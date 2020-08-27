package br.com.fiap.scheduling.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class MedicalCareDomain {

	@MongoId
	private String document;

	private Situation situation;

	private MedicalCareDomain(final Builder builder){
		this.document = builder.document;
		this.situation = builder.situation;
	}

	public String getDocument() {
		return this.document;
	}

	public Situation getSituation() {
		return situation;
	}

	public void pendingScheduling(){
		this.situation = Situation.PENDING_SCHEDULING;
	}

	public void pendingVacc(){
		this.situation = Situation.PENDING_VACC;
	}

	public void vaccinated(){
		this.situation = Situation.VACCINATED;
	}

	public MedicalCareDomain(){}

	public static final Document builder(){
		return new Builder();
	}

	public static final class Builder implements Document, Build{
		private String document;
		private Situation situation = Situation.REGISTER_PENDING;

		@Override
		public Build document(final String document) {
			this.document = document;
			return this;
		}

		@Override
		public Build situation(final Situation situation) {
			this.situation = situation;
			return this;
		}

		@Override
		public MedicalCareDomain build() {
			return new MedicalCareDomain(this);
		}
	}

	public interface Document{
		public Build document(final String document);
	}

	public interface Build{
		public Build situation(final Situation situation);
		public MedicalCareDomain build();
	}
}