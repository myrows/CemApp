package com.salesianostriana.dam.cemapp.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ValorIndicadorCentro extends ValorIndicador {
	
	
	public ValorIndicadorCentro(Double valorReal, Double valorConforme, boolean valorRealNoAplica,
			boolean valorConformeNoAplica, Colegio colegio, IndicadorCentro indicadorCentro) {
		super(valorReal, valorConforme, valorRealNoAplica, valorConformeNoAplica);
		
		this.colegio = colegio;
		this.indicadorCentro = indicadorCentro;
	}

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "colegio_id")
	private Colegio colegio;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "indicadorCentro_id")
	private IndicadorCentro indicadorCentro;
	
	

}
