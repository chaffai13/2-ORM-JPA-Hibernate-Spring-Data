package com.chaffai.ma.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezVous {

	@Id
	private String id;
	private Date dateRDV;
	
	//Pour afficher dans la BDD les valeur en String en ajoute l'annotation "@Enumerated"
	@Enumerated(EnumType.STRING)
	private StatusRDV status;

	@ManyToOne
	//Pour eviter le problème des association bidirectionnelle (dépendance cyclique) il faut ajouter (@JsonProperty)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Patient patient;
	@ManyToOne
	private Medecin medecin;
	@OneToOne(mappedBy = "rendezVous")
	private Consultation consultation;
}
