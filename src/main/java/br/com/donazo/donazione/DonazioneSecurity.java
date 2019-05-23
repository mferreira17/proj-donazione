package br.com.donazo.donazione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.donazo.donazione.seguranca.DonazioneUserDetailsService;

@Configuration
@EnableWebSecurity
public class DonazioneSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private DonazioneUserDetailsService ssUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/static/**", "/webjars/**", "/javax.faces.resource/**").permitAll().antMatchers("/pages/**")
				.hasRole("ADMINISTRADOR").antMatchers("/pages/**").hasRole("COLABORADOR").anyRequest().authenticated()
				.and().formLogin().loginPage("/index.xhtml").failureUrl("/index.xhtml?error=true").permitAll().and().rememberMe().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/pages/index.xhtml");
	
		http.csrf().disable();

	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(ssUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());

	}

//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("12345678"));
//	}
}