package com.salesianostriana.dam.cemapp.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId("api");
	}
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	
	//Falta configurar bien esta parte de permisos
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.
			httpBasic()
			.authenticationEntryPoint(authenticationEntryPoint)	
			.and()
			.authorizeRequests()
			.antMatchers("/h2-console/**").permitAll();
			http.csrf().disable();
			http.headers().frameOptions().disable();
//			.antMatchers(HttpMethod.GET, "/api/**").hasAnyRole("USER", "ADMIN")
//			.antMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
//			.antMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
//			.antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
//			.anyRequest().authenticated()
//			.and()
//			.csrf().disable();
			
	}
}
