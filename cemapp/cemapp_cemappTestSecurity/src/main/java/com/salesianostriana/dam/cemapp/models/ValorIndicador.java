package com.salesianostriana.dam.cemapp.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class ValorIndicador {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private Double valorReal;
	private Double valorConforme;
	private boolean valorRealNoAplica;
	private boolean valorConformeNoAplica;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	private PSM psm;

}
