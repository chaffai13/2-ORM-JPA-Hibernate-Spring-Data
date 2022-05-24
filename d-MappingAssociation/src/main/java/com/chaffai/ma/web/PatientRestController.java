package com.chaffai.ma.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chaffai.ma.entities.Patient;
import com.chaffai.ma.repositories.PatientRepository;

@RestController
public class PatientRestController {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping("/patients")
	public List<Patient> patientList() {
		return patientRepository.findAll();
	}

}
