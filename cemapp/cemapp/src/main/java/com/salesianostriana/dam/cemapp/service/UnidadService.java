package com.salesianostriana.dam.cemapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.models.Unidad;
import com.salesianostriana.dam.cemapp.repository.UnidadRepository;
import com.salesianostriana.dam.cemapp.service.base.BaseService;

@Service
public class UnidadService extends BaseService<Unidad, Long, UnidadRepository> {

	public Unidad encontrarPorNombre(String nombre) {
		return repositorio.findFirstByNombreContains(nombre).orElse(null);
	}

	public Boolean esExistente(String nombre) {
		return (repositorio.findFirstByNombreContains(nombre).orElse(null) == null) ? false : true;

	}
	
	

}
