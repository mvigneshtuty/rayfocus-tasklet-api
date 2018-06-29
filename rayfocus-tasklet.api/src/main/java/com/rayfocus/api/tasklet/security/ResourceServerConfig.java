package com.rayfocus.api.tasklet.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// to allow all the authorized requests
		// http.authorizeRequests().anyRequest().authenticated();
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/tasklet/admin/**").hasRole("ADMIN").anyRequest()
				.authenticated();

	}

}
