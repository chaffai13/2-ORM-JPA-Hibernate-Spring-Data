package com.chaffai.ma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chaffai.ma.entities.Medecin;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {

	Medecin findByNom(String nom);

}
