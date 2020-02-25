package com.salesianostriana.dam.cemapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cemapp.models.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	Optional<Curso> findFirstByNombreContains(String nombre); 
}
