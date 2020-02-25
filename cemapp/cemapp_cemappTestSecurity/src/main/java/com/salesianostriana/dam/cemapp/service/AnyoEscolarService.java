package com.salesianostriana.dam.cemapp.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.repository.AnyoEscolarRepository;
import com.salesianostriana.dam.cemapp.service.base.BaseService;

@Service
public class AnyoEscolarService extends BaseService<AnyoEscolar, Long, AnyoEscolarRepository> {

	public AnyoEscolar encontrarPorNombre(String nombre) {
		return repositorio.findFirstByNombreAnyoEscolarContains(nombre).orElse(null);
	}

	public Boolean esExistente(String nombre) {
		return (repositorio.findFirstByNombreAnyoEscolarContains(nombre).orElse(null) == null) ? true : false;

	}
	
	

}
