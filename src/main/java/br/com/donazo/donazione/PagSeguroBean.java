package br.com.donazo.donazione;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;

@Configuration
public class PagSeguroBean {

	@Value(value = "${pagseguro.email}")
	private String email;

	@Value(value = "${pagseguro.appKey}")
	private String appKey;

	@Value(value = "${pagseguro.appId}")
	private String appId;
	
	@Value(value = "${pagseguro.token}")
	private String token;

	@Bean
	public PagSeguro createPagSeguroInstance() {
		Credential credentials = Credential.sellerCredential(email, token);
		PagSeguro pagseguroInstance = PagSeguro.instance(credentials, PagSeguroEnv.PRODUCTION);
		return pagseguroInstance;
	}

}
