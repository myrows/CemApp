package com.salesianostriana.dam.cemapp.dto.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cemapp.dto.EtapaDto;
import com.salesianostriana.dam.cemapp.models.Etapa;
import com.salesianostriana.dam.cemapp.service.EtapaService;

/**
 * 
 * @author danib
 *
 */

@Component
public class convertEtapaDtoToEtapa {
	
	@Autowired
	private EtapaService etapaService;
	
	public Etapa newEtapa(EtapaDto etapaDto) {
		
		Etapa etapaPadre = etapaService.findById(Long.parseLong(etapaDto.getPadre()));
		
		 Etapa newEtapa = Etapa.builder()
				 .id(etapaDto.getId())
				 .nombre(etapaDto.getNombre())
				 .padre(etapaPadre)
				 .build();
		 
		 return this.etapaService.save(newEtapa);
		
	}

}
