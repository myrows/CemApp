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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

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
public class AnyoEscolar {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nombreAnyoEscolar;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fechaAnyoEscolar;


	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "anyoEscolar", fetch = FetchType.LAZY)
	private List<PSM> psm;

	

	
	@OneToMany(mappedBy="anyoEscolar")
	private List <Etapa> listaEtapas = new ArrayList<Etapa>();
	@OneToMany(mappedBy="anyoEscolar")
	private List <Proceso> listaProcesos = new ArrayList<Proceso>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			joinColumns = @JoinColumn(name="anyoEscolar_id"),
			inverseJoinColumns = @JoinColumn(name="colegio_id")
		)
	private List<Colegio> listaColegios = new ArrayList<Colegio>();
	
	
	/**
	 * Método auxiliar para manejar la asociación bidireccional con Alumno
	 * @param a
	 */
	public void addColegio(Colegio c) {
		listaColegios.add(c);
		c.getListaAnyoEscolar().add(this);
	}
	
	/**
	 * Método auxiliar para manejar la asociación bidireccional con Alumno
	 * @param a
	 */
	public void deleteColegio(Colegio c) {
		listaColegios.remove(c);
		c.getListaAnyoEscolar().remove(this);
	}
}