package com.salesianostriana.dam.cemapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.dam.cemapp.models.AppUser;
import com.salesianostriana.dam.cemapp.property.FileStorageProperties;
import com.salesianostriana.dam.cemapp.repository.AppUserRepository;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class CemappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CemappApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner init(AppUserRepository userRepo,BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			
			
			for (AppUser i : userRepo.findAll()) {
				 i.setPassword(passwordEncoder.encode(i.getPassword()));
				 userRepo.save(i);
			}
			
			
			
		};
	}

}
