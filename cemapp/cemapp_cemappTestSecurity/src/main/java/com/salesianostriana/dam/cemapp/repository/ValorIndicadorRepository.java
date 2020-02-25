package com.salesianostriana.dam.cemapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cemapp.models.ValorIndicador;

@Repository
public interface ValorIndicadorRepository extends JpaRepository<ValorIndicador, Long> {

	
	
}
