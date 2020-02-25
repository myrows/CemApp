package com.salesianostriana.dam.cemapp.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cemapp.models.IndicadorUnidad;
import com.salesianostriana.dam.cemapp.models.Proceso;
import com.salesianostriana.dam.cemapp.repository.IndicadorUnidadRepository;
import com.salesianostriana.dam.cemapp.service.base.BaseService;

@Service
public class IndicadorUnidadService extends BaseService<IndicadorUnidad, Long, IndicadorUnidadRepository> {

	public IndicadorUnidad encontrarPorNombre(String nombre) {
		return repositorio.findFirstByNombreContains(nombre).orElse(null);
	}

	public Boolean esExistente(String nombre, Proceso proceso) {

		return (repositorio.findFirstByNombreContains(nombre).orElse(null) != null)
				&& (repositorio.findFirstByNombreContains(nombre).get().getProceso().equals(proceso)) ? true : false;

	}

	public IndicadorUnidad encontrarPorNombreYProceso(String nombreIndicador, Proceso proceso) {
		// TODO Auto-generated method stub
		return this.esExistente(nombreIndicador, proceso) ? repositorio.findFirstByNombreContains(nombreIndicador).orElse(null) : null ;
	}

}
