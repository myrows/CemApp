package com.salesianostriana.dam.cemapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.cemapp.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{
	
	Optional<AppUser> findFirstByEmail(String username);

}
