package com.salesianostriana.dam.cemapp.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class IndicadorUnidad extends Indicador {

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany
	@JoinTable(name = "indicador_unidad_unidad", 
			   joinColumns = @JoinColumn(name = "indicadorUnidad_id"), 
			   inverseJoinColumns = @JoinColumn(name = "unidad_id"))
	private List<Unidad> unidad = new ArrayList<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "indicadorUnidad", fetch = FetchType.LAZY)
	private List<ValorIndicadorUnidad> listaValorIndicadorUnidad = new ArrayList<>();

}
