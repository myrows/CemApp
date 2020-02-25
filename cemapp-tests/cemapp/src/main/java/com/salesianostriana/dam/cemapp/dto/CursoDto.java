package com.salesianostriana.dam.cemapp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author danib
 *
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDto {
	
	private long id;
	private String nombre;
	private String etapa;

}
