package br.com.donazo.donazione;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;

@Configuration
@ConfigurationProperties(prefix = "pagseguro")
public class PagSeguroConfigBean {

	private String email;
	private String appKey;
	private String appId;

	@Bean
	public PagSeguro getPagSeguroBean() {
		PagSeguro pagSeguro = PagSeguro.instance(Credential.applicationCredential(appId, appKey), PagSeguroEnv.SANDBOX);
		return pagSeguro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

}
