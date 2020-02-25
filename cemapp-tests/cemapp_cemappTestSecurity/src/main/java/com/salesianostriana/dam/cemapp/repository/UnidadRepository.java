package com.salesianostriana.dam.cemapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cemapp.models.Indicador;
import com.salesianostriana.dam.cemapp.models.Unidad;

@Repository
public interface UnidadRepository extends JpaRepository<Unidad, Long> {
	Optional<Unidad> findFirstByNombreContains(String nombre); 
}
