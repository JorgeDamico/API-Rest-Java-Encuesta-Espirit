package com.espirit.encuesta.backend.auth;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableResourceServer
public class ConfigRecursos extends ResourceServerConfigurerAdapter{
	
	
	@Override

	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers(HttpMethod.GET, "/v1/clientes").permitAll()
		.antMatchers(HttpMethod.POST, "/v1/clientes").permitAll()
		.anyRequest().authenticated()
	
		.and().cors().configurationSource(corsConfigurationSource());

	}

	@Bean

	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration config = new CorsConfiguration();
		
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200")); //Arrays.asList("http://localhost:4200")
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
	
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	
		source.registerCorsConfiguration("/**", config);

	return source;

	}

	

}
