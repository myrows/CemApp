package com.salesianostriana.dam.cemapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cemapp.models.Indicador;
import com.salesianostriana.dam.cemapp.models.PSM;

@Repository
public interface PSMRepository extends JpaRepository<PSM, Long> {
	Optional<PSM> findFirstByNombreContains(String nombre); 
}
