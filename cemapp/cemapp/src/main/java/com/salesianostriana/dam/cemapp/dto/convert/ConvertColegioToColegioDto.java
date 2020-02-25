package com.salesianostriana.dam.cemapp.dto.convert;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cemapp.dto.ColegioDto;
import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.models.Colegio;
import com.salesianostriana.dam.cemapp.models.IndicadorCentro;
import com.salesianostriana.dam.cemapp.service.ColegioService;

/**
 * 
 * @author danib
 *
 */

@Component
public class ConvertColegioToColegioDto {

	@Autowired
	private ColegioService colegioService;

	public Colegio newColegio(ColegioDto colegioDto) {

		Colegio colegio = Colegio.builder().id(colegioDto.getId()).nombre(colegioDto.getNombre()).indicadoresCentro(new ArrayList<IndicadorCentro>()).listaAnyoEscolar(new ArrayList<AnyoEscolar>()).build();

		return this.colegioService.save(colegio);
	}

}
