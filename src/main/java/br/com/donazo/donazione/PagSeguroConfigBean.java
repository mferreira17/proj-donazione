package br.com.donazo.donazione;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagSeguroConfigBean {

	@Value(value = "${pagseguro.email}")
	private String email;

	@Value(value = "${pagseguro.appName}")
	private String appName;

	@Bean
	public String loadPagSeguro() {
		System.out.println(email);
		System.out.println(appName);
		return "";
	}

}
