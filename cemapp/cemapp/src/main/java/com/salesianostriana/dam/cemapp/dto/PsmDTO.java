/**
 * 
 */
package com.salesianostriana.dam.cemapp.dto;

import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.models.Colegio;
import com.salesianostriana.dam.cemapp.models.PuntoControl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jceacero
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PsmDTO {
	
	private long id;
	private String nombre;
	private String evaluacion;
	private int peso;
	private PuntoControl puntoControl;
	private Colegio colegio;
	private AnyoEscolar anyoEscolar;
	private String fileName;

}
