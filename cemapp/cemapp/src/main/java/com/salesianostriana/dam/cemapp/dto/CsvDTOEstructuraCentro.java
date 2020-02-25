package com.salesianostriana.dam.cemapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvDTOEstructuraCentro {

	private String[] etapas;
	private String curso;
	private String unidad;

}
