package com.chaffai.springDataORM.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chaffai.springDataORM.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	public List<Patient> findBySick(boolean s);
	
	public Page<Patient> findBySick(boolean s, Pageable pageable);
	
	public List<Patient> findBySickAndScoreLessThan(boolean s, int score);
	
	public List<Patient> findBySickIsTrueAndScoreLessThan(int score);
	
	public List<Patient> findByDateOfBirthBetweenAndSickIsTrueOrNameLike(Date d1, Date d2, String name);

	@Query("select p from Patient p where p.dateOfBirth between :x and :y or p.name like :z")
	public List<Patient> findPatient(@Param("x") Date d1, @Param("y") Date d2, @Param("z") String name);

}
