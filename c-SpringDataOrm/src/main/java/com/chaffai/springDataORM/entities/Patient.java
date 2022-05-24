package com.chaffai.springDataORM.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 50)
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	private boolean sick;
	private int score;
	
	public Patient(String name, Date dateOfBirth, boolean sick, int score) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.sick = sick;
		this.score = score;
	}
	
	
}
