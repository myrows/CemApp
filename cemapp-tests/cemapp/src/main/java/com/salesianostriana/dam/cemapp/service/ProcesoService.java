package com.salesianostriana.dam.cemapp.service;



import org.springframework.stereotype.Service;


import com.salesianostriana.dam.cemapp.models.Proceso;
import com.salesianostriana.dam.cemapp.repository.ProcesoRepository;
import com.salesianostriana.dam.cemapp.service.base.BaseService;

@Service
public class ProcesoService extends BaseService<Proceso, Long, ProcesoRepository> {

	public Proceso encontrarPorNombre(String nombre) {
		return repositorio.findFirstByNombreContains(nombre).orElse(null);
	}

	public Boolean esExistente(String nombre) {
		return (repositorio.findFirstByNombreContains(nombre).orElse(null) == null) ? false : true;

	}
	
	

}
