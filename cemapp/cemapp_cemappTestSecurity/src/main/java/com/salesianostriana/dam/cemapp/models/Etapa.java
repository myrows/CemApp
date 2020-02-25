package com.salesianostriana.dam.cemapp.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Etapa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nombre;
	private int peso;

	@NotNull
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy="etapa", fetch = FetchType.LAZY)
	private List<Curso> cursos= new ArrayList<>();
	
	//Puede petar
	@ManyToOne
	private Etapa padre;
	
	



}
