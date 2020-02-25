package com.salesianostriana.dam.cemapp.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Colegio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private long id;

	private String nombre;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "colegio", fetch = FetchType.LAZY)
	private List<PSM> psm = new ArrayList<>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "colegio", fetch = FetchType.LAZY)
	private List<PuntoControl> pControl = new ArrayList<>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "colegio", fetch = FetchType.LAZY)
	private List<ValorIndicadorCentro> vIndicadorCentro = new ArrayList<>();

	@NotNull
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany
	private List<Etapa> etapas;

	@ManyToOne
	private AnyoEscolar anyoEscolar;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "colegios")
	private List<IndicadorCentro> indicadoresCentro = new ArrayList<>();
	
	@NotNull
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "colegio", fetch = FetchType.LAZY)
	private List<AppUser> usuarios = new ArrayList<>();
}
