package br.com.donazo.donazione.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.donazo.donazione.seguranca.DonazioneUserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DonazioneUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				.antMatchers("/javax.faces.resource/**", "/publica/**").permitAll()
				.antMatchers("/pages").hasAnyRole("ADMINISTRADOR", "COLABORADOR")
				.anyRequest()
				.authenticated()
			.and()
			.formLogin()
				.loginPage("/index.xhtml")
				.permitAll().successForwardUrl("/pages/index.xhtml")
				.defaultSuccessUrl("/pages/index.xhtml", true)
			.and()
			.logout()
				.logoutSuccessUrl("/index.xhtml?logout")
				.permitAll()
			.and()
			.rememberMe()
				.userDetailsService(userDetailsService);
		
		http.csrf().disable();
	}

}
