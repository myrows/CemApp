package com.salesianostriana.dam.cemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cemapp.models.PuntoControl;

@Repository
public interface PuntoControlRepository extends JpaRepository<PuntoControl, Long> {
	
}
