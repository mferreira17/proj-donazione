package br.com.donazo.donazione.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.donazo.donazione.seguranca.DonazioneUserDetailsService;

@Configuration
public class UdsSecurityConfig {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder, 
			DonazioneUserDetailsService userDetailsService) throws Exception {
		builder
			.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
//	public static void main(String[] args) {
//	System.out.println(new BCryptPasswordEncoder().encode("12345678"));
//}
}
