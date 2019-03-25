package br.com.donazo.donazione;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DonazioneApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonazioneApplication.class, args);
	}

}
