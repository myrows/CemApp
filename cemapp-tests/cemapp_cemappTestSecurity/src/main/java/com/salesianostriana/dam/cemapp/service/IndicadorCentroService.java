package com.salesianostriana.dam.cemapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.models.IndicadorCentro;
import com.salesianostriana.dam.cemapp.repository.IndicadorCentroRepository;
import com.salesianostriana.dam.cemapp.service.base.BaseService;

@Service
public class IndicadorCentroService extends BaseService<IndicadorCentro, Long, IndicadorCentroRepository> {

	public IndicadorCentro encontrarPorNombre(String nombre) {
		return repositorio.findFirstByNombreContains(nombre).orElse(null);
	}

	public Boolean esExistente(String nombre) {
		return (repositorio.findFirstByNombreContains(nombre).orElse(null) == null) ? true : false;

	}
	
	

}
