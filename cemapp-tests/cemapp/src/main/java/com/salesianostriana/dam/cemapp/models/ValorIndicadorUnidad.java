package com.salesianostriana.dam.cemapp.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ValorIndicadorUnidad extends ValorIndicador {
	
	public ValorIndicadorUnidad(Double valorReal, Double valorConforme, boolean valorRealNoAplica,
			boolean valorConformeNoAplica, Unidad unidad, IndicadorUnidad indicadorUnidad) {
		super(valorReal, valorConforme, valorRealNoAplica, valorConformeNoAplica);
		
		this.unidad = unidad;
		this.indicadorUnidad = indicadorUnidad;
	}
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unidad_id")
	private Unidad unidad;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "indicadorUnidad_id")
	private IndicadorUnidad indicadorUnidad;

}
