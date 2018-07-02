package com.rayfocus.api.tasklet.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.rayfocus.api.tasklet.auth.config.ServiceConfig;

@Configuration
public class JWTTokenStoreConfig {
	
	@Autowired
	private ServiceConfig serviceConfig;
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtTokenAccessConverter());
	}
	
	@Bean
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(tokenStore());
		tokenServices.setSupportRefreshToken(true);
		return null;
	}
	
	@Bean
	public JwtAccessTokenConverter jwtTokenAccessConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(serviceConfig.getJwtSigningKey());
		return jwtAccessTokenConverter;
	}
	
	@Bean
	public TokenEnhancer jwtTokenEnhancer() {
		return new JWTTokenEnhancer();
	}
}