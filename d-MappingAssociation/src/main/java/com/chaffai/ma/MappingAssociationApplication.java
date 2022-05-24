package com.chaffai.ma;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.chaffai.ma.entities.Consultation;
import com.chaffai.ma.entities.Medecin;
import com.chaffai.ma.entities.Patient;
import com.chaffai.ma.entities.RendezVous;
import com.chaffai.ma.entities.StatusRDV;
import com.chaffai.ma.repositories.MedecinRepository;
import com.chaffai.ma.repositories.PatientRepository;
import com.chaffai.ma.repositories.RendezVousRepository;
import com.chaffai.ma.services.IHospitalService;

@SpringBootApplication
public class MappingAssociationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MappingAssociationApplication.class, args);
	}


	//La méthode qui utilise l'annotation "@Bean" => une méthode qui s"exécute au démarrage 
	//Cette méthode va retourner un objet qui devient un composant Spring
	@Bean
	CommandLineRunner start(IHospitalService hospitalService,
							PatientRepository patientRepository,
							MedecinRepository medecinRepository,
							RendezVousRepository rendezVousRepository) {

		return args -> {
			
			// 1) Ajouter des Patients
			Stream.of("Mohammed", "Farid", "Amelie").forEach(name -> {
				Patient patient = new Patient();
				patient.setNom(name);
				patient.setDateNaissance(new Date());
				patient.setMalade(false);

				hospitalService.savePatient(patient);
			});

			// 2) Ajouter des Médecins
			Stream.of("Jacob", "Detouche", "Solange").forEach(name -> {
				Medecin medecin = new Medecin();
				medecin.setNom(name);
				medecin.setEmail(name + "@gmail.com");
				medecin.setSpecialite(Math.random() > 0.5 ? "Cardio" : "Dentiste");

				hospitalService.saveMedecin(medecin);
			});

			// 3) Ajouter un Rendez-vous

			// Chercher un Patient
			Patient patient = patientRepository.findById(2L).orElse(null);
			Patient patient1 = patientRepository.findByNom("Mohammed");

			/// Chercher un Médecin
			Medecin medecin = medecinRepository.findByNom("Detouche");

			// Créer un RendezVous
			RendezVous rendezVous = new RendezVous();
			rendezVous.setDateRDV(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			RendezVous saveRDV = 	hospitalService.saveRDV(rendezVous);
			System.out.println(saveRDV.getId());
			
			//3) Ajouter une Consultation
			//RendezVous rendezVous1 = rendezVousRepository.findById(1L).orElse(null);
			RendezVous rendezVous1 = rendezVousRepository.findAll().get(0);
			Consultation consultation = new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de consultation Rendez-Vous N°= 1");
			hospitalService.saveConsultation(consultation);			
		};

	}
}
