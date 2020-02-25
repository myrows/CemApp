package com.salesianostriana.dam.cemapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cemapp.models.AnyoEscolar;

@Repository
public interface AnyoEscolarRepository extends JpaRepository<AnyoEscolar, Long> {
	Optional<AnyoEscolar> findFirstByNombreAnyoEscolarContains(String nombre); 
}
