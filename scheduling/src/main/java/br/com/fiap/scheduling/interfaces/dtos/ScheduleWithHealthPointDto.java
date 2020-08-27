package br.com.fiap.scheduling.interfaces.dtos;

public class ScheduleWithHealthPointDto extends ScheduleDto{

	private HealthPointDto healthPoint;

	public HealthPointDto getHealthPoint() {
		return healthPoint;
	}

	public void setHealthPoint(HealthPointDto healthPoint) {
		this.healthPoint = healthPoint;
	}
}
