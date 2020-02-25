package com.salesianostriana.dam.cemapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.models.PSM;
import com.salesianostriana.dam.cemapp.repository.PSMRepository;
import com.salesianostriana.dam.cemapp.service.base.BaseService;

@Service
public class PSMService extends BaseService<PSM, Long, PSMRepository> {

	public PSM encontrarPorNombre(String nombre) {
		return repositorio.findFirstByNombreContains(nombre).orElse(null);
	}

	public Boolean esExistente(String nombre) {
		return (repositorio.findFirstByNombreContains(nombre).orElse(null) == null) ? true : false;

	}
	

}
