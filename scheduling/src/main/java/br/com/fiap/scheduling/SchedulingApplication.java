package br.com.fiap.scheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { SchedulingApplication.BASE_PACKAGE })
public class SchedulingApplication {

	public static final String BASE_PACKAGE = "br.com.fiap.scheduling";

	public static void main(String[] args) {
		SpringApplication.run(SchedulingApplication.class, args);
	}

}
