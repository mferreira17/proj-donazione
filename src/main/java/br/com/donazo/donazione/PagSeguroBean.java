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

	@Bean
	public PagSeguro createPagSeguroInstance() {
		Credential credentials = Credential.applicationCredential(appId, appKey);
		PagSeguro pagseguroInstance = PagSeguro.instance(credentials, PagSeguroEnv.PRODUCTION);
		return pagseguroInstance;
	}

}
