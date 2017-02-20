/**
 * 
 */
package com.emc.it.ooa.sample;

import org.crsh.shell.impl.command.system.help;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.emc.it.ooa.sample.csrf.CsrfHeaderFilter;
import com.emc.it.ooa.sample.xss.XSSFilterNew;

/**
 * @author elumam
 * 
 */

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties security;

	// private SpringModel obj;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter
	 * #configure(org.springframework.security.config
	 * .annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().defaultSuccessUrl("/loginsuccess.html").and().logout().and().authorizeRequests()
				.antMatchers("/login", "/index", "/test").hasAnyRole("user", "admin")
				.antMatchers("/ShowUsers").hasRole("admin")
				/*.antMatchers("/adminPage/*").hasRole("admin")*/
				.and().authorizeRequests().antMatchers("/admin")
				.hasRole("admin").and().authorizeRequests().antMatchers("/test").hasRole("admin").and()
				.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
				.csrf().csrfTokenRepository(csrfTokenRepository());
		
			
	

	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter
	 * #configure(org.springframework.security.config
	 * .annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder builder)
			throws Exception {
		builder.inMemoryAuthentication().withUser("user").password("password1")
				.roles("user");
		builder.inMemoryAuthentication().withUser("admin")
				.password("password1").roles("admin");

	}

}
