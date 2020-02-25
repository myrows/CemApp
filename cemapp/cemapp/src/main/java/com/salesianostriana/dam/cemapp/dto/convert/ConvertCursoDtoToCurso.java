package com.salesianostriana.dam.cemapp.dto.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cemapp.dto.AnyoEscolarListarDTO;
import com.salesianostriana.dam.cemapp.dto.CursoDto;
import com.salesianostriana.dam.cemapp.dto.CursoListDto;
import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.models.Curso;
import com.salesianostriana.dam.cemapp.models.Etapa;
import com.salesianostriana.dam.cemapp.service.CursoService;
import com.salesianostriana.dam.cemapp.service.EtapaService;


@Component
public class ConvertCursoDtoToCurso {
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private EtapaService etapaService;
	
	public List<CursoListDto> listCurso(List<Curso> lista) {

		List<CursoListDto> listaDto = new ArrayList<CursoListDto>();

		for (Curso c : lista) {
			
			Etapa e = etapaService.findById(c.getEtapa().getId());
			
			CursoListDto dto = new CursoListDto();
			
			dto.setId(c.getId());
			dto.setNombre(c.getNombre());
			dto.setPeso(c.getPeso());
			dto.setIdEtapa(e.getId());
			
			listaDto.add(dto);
		}

		return listaDto;
	}
	
	public Curso newCurso(CursoDto cursoDto) {
		
		Etapa etapa = etapaService.findById(Long.parseLong(cursoDto.getEtapa()));
		
		Curso newCurso = Curso.builder()
				.id(cursoDto.getId())
				.nombre(cursoDto.getNombre())
				.etapa(etapa)
				.build();
		return this.cursoService.save(newCurso);
	}

}
