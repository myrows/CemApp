package com.salesianostriana.dam.cemapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.models.IndicadorUnidad;
import com.salesianostriana.dam.cemapp.repository.IndicadorUnidadRepository;
import com.salesianostriana.dam.cemapp.service.base.BaseService;

@Service
public class IndicadorUnidadService extends BaseService<IndicadorUnidad, Long, IndicadorUnidadRepository> {

	public IndicadorUnidad encontrarPorNombre(String nombre) {
		return repositorio.findFirstByNombreContains(nombre).orElse(null);
	}

	public Boolean esExistente(String nombre) {
		return (repositorio.findFirstByNombreContains(nombre).orElse(null) == null) ? true : false;

	}
	

}
