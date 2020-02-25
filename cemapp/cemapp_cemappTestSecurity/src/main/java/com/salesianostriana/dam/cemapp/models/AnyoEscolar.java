package com.salesianostriana.dam.cemapp.models;

import java.time.LocalDate;
import java.util.ArrayList;
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
public class AnyoEscolar {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nombreAnyoEscolar;
	private LocalDate fechaAnyoEscolar;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "anyoEscolar", fetch = FetchType.LAZY)
	private List<PSM> psm;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "anyoEscolar", fetch = FetchType.LAZY)
	private List<PuntoControl> pControl;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "anyoEscolar", fetch = FetchType.LAZY)
	private List<ValorIndicadorCentro> vIndicadorCentro;

}
