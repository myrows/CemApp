package com.salesianostriana.dam.cemapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.cemapp.models.Colegio;

public interface ColegioRepository extends JpaRepository<Colegio, Long> {
	Optional<Colegio> findFirstByNombreContains(String nombre); 
}
