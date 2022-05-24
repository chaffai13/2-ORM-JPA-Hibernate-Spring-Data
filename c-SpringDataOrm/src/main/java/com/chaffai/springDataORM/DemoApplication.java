package com.chaffai.springDataORM;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.chaffai.springDataORM.dao.PatientRepository;
import com.chaffai.springDataORM.entities.Patient;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private PatientRepository patientRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Ajouter des patients
		for (int i = 0; i < 50; i++) {
			patientRepository.save(new Patient("hassan", new Date(), Math.random()>0.5?true:false, (int)(Math.random()*100)));
		}
		
		//Consulter tous les patients
		//List<Patient> patients = patientRepository.findAll();
		Page<Patient> patients = patientRepository.findAll(PageRequest.of(0, 5));
		System.out.println("Total pages: " + patients.getTotalPages());
		System.out.println("Total elements: " + patients.getTotalElements());
		System.out.println("Num Page: " + patients.getNumber());
		List<Patient> content = patients.getContent();
		content.forEach(p->{
			System.out.println(p.toString());;
		});
		System.out.println("******************************************************");
		
		//Consulter tous les patients
		//Patient patient = patientRepository.findById(2L).get();
		//Patient patient = patientRepository.findById(2L).orElseThrow(()->new RuntimeException("Le patient n'existe pas dans la BDD"));
		Patient patient = patientRepository.findById(2L).orElse(null);
		
		//Mettre a jour
		patient.setScore(999);
		patientRepository.save(patient);
		if (patient!=null) {
			System.out.println(patient.getName());
		}
		
		//Supprimer un patient
		patientRepository.deleteById(1L);
		System.out.println("******************************************************");
		
		//Consulter tous les patients malades 
//		List<Patient> bySick = patientRepository.findBySick(true);
		Page<Patient> bySick = patientRepository.findBySick(true, PageRequest.of(0, 5));
		
		bySick.forEach(p->{
		System.out.println(p.toString());
	});
		
	}

}
