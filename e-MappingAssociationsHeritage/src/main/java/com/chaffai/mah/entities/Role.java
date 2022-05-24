package com.chaffai.mah.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="DESCRIPTION")
	private String desc;
	
	@Column(length = 30, unique = true)
	private String roleName;
	
	//Si on utilise EAGER il faut toujours initialiser les variables
	//Pour eviter un NullPointerException	
	@ManyToMany(fetch = FetchType.EAGER)
	//@JoinTable(name = "USERS_ROLES")
	@ToString.Exclude
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<User> users = new ArrayList<>();

}
