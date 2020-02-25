package com.salesianostriana.dam.cemapp.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

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
public class PSM {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre;
	private String evaluacion;
	private int peso;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "puntoControl_id")
	private PuntoControl puntoControl;

	@NotNull
	@EqualsAndHashCode.Exclude
	@ToString.Exclude

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "colegio_id")
	private Colegio colegio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anyoEscolar_id")
	private AnyoEscolar anyoEscolar;

	
	@OneToMany(mappedBy = "psm", fetch = FetchType.LAZY)
	private List <ValorIndicador> valoresIndicadores;
	
}
