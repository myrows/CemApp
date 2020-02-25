package com.salesianostriana.dam.cemapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cemapp.dto.AppUserDto;
import com.salesianostriana.dam.cemapp.error.UserNotFoundException;
import com.salesianostriana.dam.cemapp.models.AppUser;
import com.salesianostriana.dam.cemapp.repository.AppUserRepository;
import com.salesianostriana.dam.cemapp.service.base.BaseService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AppUserService extends BaseService<AppUser, Long, AppUserRepository>{
	

	public AppUser getAppUseryByEmail(String username)  {
		return this.repositorio.findFirstByEmail(username).orElseThrow(() -> new UserNotFoundException(username));
	}
	

}
