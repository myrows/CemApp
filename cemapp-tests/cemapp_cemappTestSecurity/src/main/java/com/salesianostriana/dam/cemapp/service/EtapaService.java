package com.salesianostriana.dam.cemapp.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cemapp.models.Etapa;
import com.salesianostriana.dam.cemapp.repository.EtapaRepository;
import com.salesianostriana.dam.cemapp.service.base.BaseService;

@Service
public class EtapaService extends BaseService<Etapa, Long, EtapaRepository> {

	public Etapa encontrarPorNombre(String nombre) {
		return repositorio.findFirstByNombreContains(nombre).orElse(null);
	}

	public Boolean esExistente(String nombre) {
		return (repositorio.findFirstByNombreContains(nombre).orElse(null) == null) ? true : false;

	}
	
	

}
