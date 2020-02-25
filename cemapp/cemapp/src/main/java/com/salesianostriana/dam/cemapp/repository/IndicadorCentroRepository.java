package com.salesianostriana.dam.cemapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cemapp.models.Indicador;
import com.salesianostriana.dam.cemapp.models.IndicadorCentro;

@Repository
public interface IndicadorCentroRepository extends JpaRepository<IndicadorCentro, Long> {
	Optional<IndicadorCentro> findFirstByNombreContains(String nombre); 
}
