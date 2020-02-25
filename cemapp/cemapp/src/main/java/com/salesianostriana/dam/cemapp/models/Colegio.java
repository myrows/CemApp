package com.salesianostriana.dam.cemapp.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Colegio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private long id;

	private String nombre;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	@OneToMany(mappedBy = "colegio", fetch = FetchType.LAZY)
	private List<PSM> psm = new ArrayList<PSM>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	@OneToMany(mappedBy = "colegio", fetch = FetchType.LAZY)
	private List<PuntoControl> pControl = new ArrayList<PuntoControl>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	@OneToMany(mappedBy = "colegio", fetch = FetchType.LAZY)
	private List<ValorIndicadorCentro> vIndicadorCentro = new ArrayList<ValorIndicadorCentro>();
	
	//TODO notNull
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	@OneToMany
	private List<Etapa> etapas;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	@ManyToMany(mappedBy = "listaColegios", fetch = FetchType.LAZY)
	private List <AnyoEscolar> listaAnyoEscolar= new ArrayList<AnyoEscolar>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "colegios")
	private List<IndicadorCentro> indicadoresCentro = new ArrayList<IndicadorCentro>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	@OneToMany(mappedBy="colegio")
	private List<AppUser> listaUser = new ArrayList<AppUser>();
}
