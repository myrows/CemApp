package com.salesianostriana.dam.cemapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cemapp.models.Colegio;
import com.salesianostriana.dam.cemapp.models.Etapa;
import com.salesianostriana.dam.cemapp.repository.EtapaRepository;
import com.salesianostriana.dam.cemapp.service.base.BaseService;

@Service
public class EtapaService extends BaseService<Etapa, Long, EtapaRepository> {
	

	public Etapa encontrarPorNombre(String nombre) {
		return repositorio.findFirstByNombreContains(nombre).orElse(null);
	}

	public Boolean esExistente(String nombre) {
		return (repositorio.findFirstByNombreContains(nombre).orElse(null) == null) ? false : true;
	}
	
	public List<Etapa> searchByPadreNull() {
		return repositorio.findAllByPadreNull();
	}
	
	
	
	
	
	

}
