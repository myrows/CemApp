package com.salesianostriana.dam.cemapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Data
public class ValorIndicador {
	
	
	public ValorIndicador(Double valorReal, Double valorConforme, boolean valorRealNoAplica,
			boolean valorConformeNoAplica) {
		this.valorReal = valorReal;
		this.valorConforme = valorConforme;
		this.valorRealNoAplica = valorRealNoAplica;
		this.valorConformeNoAplica = valorConformeNoAplica;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private Double valorReal;
	private Double valorConforme;
	private boolean valorRealNoAplica;
	private boolean valorConformeNoAplica;
}
