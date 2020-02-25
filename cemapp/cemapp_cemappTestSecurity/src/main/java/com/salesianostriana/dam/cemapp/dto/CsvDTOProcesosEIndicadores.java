package com.salesianostriana.dam.cemapp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class CsvDTOProcesosEIndicadores {
	private String nombreProceso;
	private String nombreIndicador;
	private String etapa;
	private String ciclo;
	private String curso;
	private String unidad;
	private String valorR;
	private String valorC;

}
