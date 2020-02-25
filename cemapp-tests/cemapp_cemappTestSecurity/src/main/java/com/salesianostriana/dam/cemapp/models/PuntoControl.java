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
public class PuntoControl {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private LocalDate fecha;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "puntoControl", fetch = FetchType.LAZY)
	private List<PSM> psm = new ArrayList<>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "colegio_id")
	private Colegio colegio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anyoEscolar_id")
	private AnyoEscolar anyoEscolar;

}
