package com.salesianostriana.dam.cemapp.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cemapp.models.Indicador;
import com.salesianostriana.dam.cemapp.repository.IndicadorRepository;
import com.salesianostriana.dam.cemapp.service.base.BaseService;

@Service
public class IndicadorService extends BaseService<Indicador, Long, IndicadorRepository> {

	public Indicador encontrarPorNombre(String nombre) {
		return repositorio.findFirstByNombreContains(nombre).orElse(null);
	}

	public Boolean esExistente(String nombre) {
		return (repositorio.findFirstByNombreContains(nombre).orElse(null) == null) ? true : false;

	}

}
