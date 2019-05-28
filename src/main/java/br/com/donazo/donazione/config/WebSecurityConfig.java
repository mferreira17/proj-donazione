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
				.antMatchers("/autenticado/pages/campanha/**").hasAnyRole("ADMINISTRADOR")
				.antMatchers("/autenticado/pages/colaborador/**").hasAnyRole("ADMINISTRADOR")
				.antMatchers("/autenticado/pages/itemdoacao/**").hasAnyRole("ADMINISTRADOR")
				.antMatchers("/autenticado/pages/evento/lista/**").hasAnyRole("COLABORADOR", "ADMINISTRADOR")
				.antMatchers("/autenticado/pages/evento/**").hasAnyRole("ADMINISTRADOR")
				.antMatchers("/autenticado/pages/doacao/**").hasAnyRole("ADMINISTRADOR", "COLABORADOR")
				.antMatchers("/autenticado").hasAnyRole("ADMINISTRADOR", "COLABORADOR")
				.anyRequest()
				.authenticated()
			.and()
			.formLogin()
				.loginPage("/index.xhtml")
				.failureUrl("/index.xhtml?error=true")
				.permitAll().successForwardUrl("/autenticado/dashboard.xhtml")
				.defaultSuccessUrl("/autenticado/dashboard.xhtml", true)
			.and()
			.logout()
				.logoutSuccessUrl("/index.xhtml?logout=true")
				.permitAll()
			.and()
			.rememberMe()
				.userDetailsService(userDetailsService);
		
		http.csrf().disable();
	}

}