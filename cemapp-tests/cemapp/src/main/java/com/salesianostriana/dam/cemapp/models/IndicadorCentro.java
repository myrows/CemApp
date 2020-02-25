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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class IndicadorCentro extends Indicador {
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "indicadorCentro", fetch = FetchType.LAZY)
	private List<ValorIndicadorCentro> listaValorIndicadorCentro = new ArrayList<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "indicador_centro_colegio", 
			   joinColumns = @JoinColumn(name = "indicadorCentro_id"), 
			   inverseJoinColumns = @JoinColumn(name = "colegio_id"))
	private List<Colegio> colegios = new ArrayList<>();
	
	
}
