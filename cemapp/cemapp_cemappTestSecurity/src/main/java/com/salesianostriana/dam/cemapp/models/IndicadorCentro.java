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
public class IndicadorCentro extends Indicador {
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "indicadorCentro", fetch = FetchType.LAZY)
	private List<ValorIndicadorCentro> listaValorIndicadorCentro = new ArrayList<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany
	@JoinTable(name = "indicador_centro_colegio", 
			   joinColumns = @JoinColumn(name = "indicadorCentro_id"), 
			   inverseJoinColumns = @JoinColumn(name = "colegio_id"))
	private List<Colegio> colegios = new ArrayList<>();
	
	//TODO mirar por qué en H2 no aparecen los atributos de nombre y porcentaje
	public IndicadorCentro(long id, String nombre, boolean esPorcentaje, Proceso proceso) {
		super(id, nombre, esPorcentaje, proceso);
	}
	
	
}
