package com.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class webSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailsService());
		return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/auth/**","skyscrapers/home","/images/**","/fonts/**","/vendor/**","/public/**","/css/**","/js/**").permitAll()
			.antMatchers("/skyscrapers/consultarDocumentos").hasAnyAuthority("CLI_GESTOR","CLI_FIRMANTE")
			.antMatchers("/skyscrapers/documentos/listarDocumentos").hasAnyAuthority("CLI_FIRMANTE")
			.antMatchers("/skyscrapers/reporteEstadisticos").hasAnyAuthority("CLI_FIRMANTE")
			.antMatchers("/skyscrapers/subirDocumento").hasAnyAuthority("CLI_GESTOR")
			.antMatchers("/skyscrapers/mantenimientoTrabajador").hasAnyAuthority("CLI_GESTOR")
			.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/auth/login").defaultSuccessUrl("/skyscrapers/home",true).failureUrl("/auth/login?error=true")
				.loginProcessingUrl("/auth/login-post").permitAll()
			.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login")
			.and()
				.exceptionHandling().accessDeniedPage("/403")
			;
	} 
	
	
	
	
}
