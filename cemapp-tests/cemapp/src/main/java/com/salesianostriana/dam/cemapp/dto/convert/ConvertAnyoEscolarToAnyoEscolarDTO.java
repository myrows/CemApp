/**
 * 
 */
package com.salesianostriana.dam.cemapp.dto.convert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cemapp.dto.AnyoEscolarListarDTO;
import com.salesianostriana.dam.cemapp.dto.AnyoEscolarNewDTO;
import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.models.Colegio;
import com.salesianostriana.dam.cemapp.service.AnyoEscolarService;
import com.salesianostriana.dam.cemapp.service.ColegioService;

/**
 * @author cads9
 *
 */

@Component
public class ConvertAnyoEscolarToAnyoEscolarDTO {

	@Autowired
	private ColegioService colegioService;

	@Autowired
	private AnyoEscolarService anyoEscolarService;

	public List<AnyoEscolarListarDTO> listAnyos(List<AnyoEscolar> lista) {

		List<AnyoEscolarListarDTO> l = new ArrayList<AnyoEscolarListarDTO>();

		for (AnyoEscolar a : lista) {
			AnyoEscolarListarDTO dto = new AnyoEscolarListarDTO();
			dto.setId(a.getId());
			dto.setNombreAnyoEscolar(a.getNombreAnyoEscolar());
			dto.setFechaAnyoEscolar(a.getFechaAnyoEscolar());
			l.add(dto);
		}

		return l;
	}

	public AnyoEscolar addAnyo(AnyoEscolarNewDTO dto) {
		AnyoEscolar a = new AnyoEscolar();
		List<Colegio> lista = colegioService.findAll();
		a.setNombreAnyoEscolar(dto.getNombreAnyoEscolar());
		a.setFechaAnyoEscolar(LocalDate.now());
		for (Colegio c : lista) {
			a.addColegio(c);
		}
		anyoEscolarService.save(a);
		return a;

	}
	
	public AnyoEscolar editAnyo(AnyoEscolarNewDTO dto, Long id) {
		AnyoEscolar a = anyoEscolarService.findById(id);
		a.setNombreAnyoEscolar(dto.getNombreAnyoEscolar());
		anyoEscolarService.save(a);
		return a;
	}

}
