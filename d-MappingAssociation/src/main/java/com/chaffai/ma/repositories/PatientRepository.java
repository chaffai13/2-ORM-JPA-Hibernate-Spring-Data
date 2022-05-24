package com.chaffai.ma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chaffai.ma.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	Patient findByNom(String nom);
}
