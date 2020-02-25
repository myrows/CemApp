package com.salesianostriana.dam.cemapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cemapp.models.Etapa;
import com.salesianostriana.dam.cemapp.models.Indicador;

@Repository
public interface EtapaRepository extends JpaRepository<Etapa, Long> {
	Optional<Etapa> findFirstByNombreContains(String nombre); 
}
