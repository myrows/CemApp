package com.salesianostriana.dam.cemapp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.salesianostriana.dam.cemapp.error.CustomAccessDeniedHandler;
import com.salesianostriana.dam.cemapp.error.CustomAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableResourceServer
@RequiredArgsConstructor
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId("api");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/h2-console/**").permitAll().antMatchers("/api/**").authenticated().anyRequest()
				.authenticated()
				// .antMatcher("/**")
				// .authorizeRequests().antMatchers("/h2-console/**").permitAll()
				.and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
				.accessDeniedHandler(new CustomAccessDeniedHandler());

		http.cors().and().csrf().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

}
