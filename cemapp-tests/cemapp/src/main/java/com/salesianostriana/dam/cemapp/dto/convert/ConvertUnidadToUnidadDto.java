package com.salesianostriana.dam.cemapp.dto.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cemapp.dto.CursoListDto;
import com.salesianostriana.dam.cemapp.dto.UnidadDto;
import com.salesianostriana.dam.cemapp.dto.UnidadListDto;
import com.salesianostriana.dam.cemapp.models.Curso;
import com.salesianostriana.dam.cemapp.models.Etapa;
import com.salesianostriana.dam.cemapp.models.Unidad;
import com.salesianostriana.dam.cemapp.service.CursoService;
import com.salesianostriana.dam.cemapp.service.UnidadService;

@Component
public class ConvertUnidadToUnidadDto {
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private UnidadService unidadService;
	
	public List<UnidadListDto> listUnidad(List<Unidad> lista) {

		List<UnidadListDto> listaDto = new ArrayList<UnidadListDto>();

		for (Unidad u : lista) {
			
			Curso c = cursoService.findById(u.getCurso().getId());
			
			UnidadListDto dto = new UnidadListDto();
			
			dto.setId(u.getId());
			dto.setNombre(u.getNombre());
			dto.setPeso(u.getPeso());
			dto.setIdCurso(c.getId());
			
			listaDto.add(dto);
		}

		return listaDto;
	}
	
	public Unidad newUnidad(UnidadDto unidadDto) {
		
		Curso curso = cursoService.findById(Long.parseLong(unidadDto.getCurso()));
		
		Unidad newUnidad = Unidad.builder()
				.id(unidadDto.getId())
				.nombre(unidadDto.getNombre())
				.curso(curso)
				.build();
		return this.unidadService.save(newUnidad);
	}

}
