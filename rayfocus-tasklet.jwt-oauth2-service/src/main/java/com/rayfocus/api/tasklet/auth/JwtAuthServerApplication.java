package com.rayfocus.api.tasklet.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
public class JwtAuthServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(JwtAuthServerApplication.class, args);
	}
}
