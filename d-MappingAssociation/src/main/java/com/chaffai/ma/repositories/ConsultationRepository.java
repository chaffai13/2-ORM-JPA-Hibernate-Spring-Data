package com.chaffai.ma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chaffai.ma.entities.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

}
