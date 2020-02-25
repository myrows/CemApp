package com.salesianostriana.dam.cemapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cemapp.models.Colegio;
import com.salesianostriana.dam.cemapp.models.Etapa;

/**
 * 
 * @author danib
 *
 */
@Repository
public interface EtapaRepository extends JpaRepository<Etapa, Long> {

	Optional<Etapa> findFirstByNombreContains(String nombre);

	public List<Etapa> findAllByPadreNull();

	// TODO Hacer todas las consultas

}
