package com.salesianostriana.dam.cemapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cemapp.models.Indicador;

@Repository
public interface IndicadorRepository extends JpaRepository<Indicador, Long> {
	
	Optional<Indicador> findFirstByNombreContains(String nombre); 

}
