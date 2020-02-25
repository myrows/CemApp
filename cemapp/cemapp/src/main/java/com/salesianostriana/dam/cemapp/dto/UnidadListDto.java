package com.salesianostriana.dam.cemapp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadListDto {
	
	private long id;
	private String nombre;
	private int peso;
	private Long idCurso;

}
