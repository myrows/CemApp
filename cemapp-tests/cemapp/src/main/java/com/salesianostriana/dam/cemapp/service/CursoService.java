package com.salesianostriana.dam.cemapp.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cemapp.models.Curso;
import com.salesianostriana.dam.cemapp.repository.CursoRepository;
import com.salesianostriana.dam.cemapp.service.base.BaseService;

@Service
public class CursoService extends BaseService<Curso, Long, CursoRepository> {
	
	public Curso encontrarPorNombre(String nombre) {
		return repositorio.findFirstByNombreContains(nombre).orElse(null);
	}

	public Boolean esExistente(String nombre) {
		return (repositorio.findFirstByNombreContains(nombre).orElse(null) == null) ? false : true;

	}
	
	

}
