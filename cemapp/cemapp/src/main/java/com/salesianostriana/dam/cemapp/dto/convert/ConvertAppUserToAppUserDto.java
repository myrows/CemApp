package com.salesianostriana.dam.cemapp.dto.convert;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cemapp.dto.AppUserDto;
import com.salesianostriana.dam.cemapp.models.AppUser;
import com.salesianostriana.dam.cemapp.models.Colegio;
import com.salesianostriana.dam.cemapp.service.AppUserService;
import com.salesianostriana.dam.cemapp.service.ColegioService;

/**
 * 
 * @author danib
 *
 */

@Component
public class ConvertAppUserToAppUserDto {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private ColegioService colegioService;
	
	public AppUser newAppUser(AppUserDto appUserDto) {
		
		Colegio colegio = colegioService.findById(Long.parseLong(appUserDto.getColegio()));
		AppUser appUser = AppUser.builder()
				.id(appUserDto.getId())
				.email(appUserDto.getEmail())
				.password(passwordEncoder.encode(appUserDto.getPassword()))
				.roles(new HashSet<String>())
				.colegio(colegio)
				.build();
		appUser.getRoles().add(appUserDto.getRoles());
		return this.appUserService.save(appUser);
	}
	
	

}
