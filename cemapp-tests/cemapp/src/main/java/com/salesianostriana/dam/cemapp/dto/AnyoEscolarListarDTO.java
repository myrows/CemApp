/**
 * 
 */
package com.salesianostriana.dam.cemapp.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cads9
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AnyoEscolarListarDTO {
	
	private Long id;
	private String nombreAnyoEscolar;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate fechaAnyoEscolar;
	
	private Long idColegio;

}
