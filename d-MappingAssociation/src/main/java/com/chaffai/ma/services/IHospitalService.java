package com.chaffai.ma.services;

import com.chaffai.ma.entities.Consultation;
import com.chaffai.ma.entities.Medecin;
import com.chaffai.ma.entities.Patient;
import com.chaffai.ma.entities.RendezVous;

public interface IHospitalService {

	Patient savePatient(Patient patient);
	Medecin saveMedecin (Medecin medecin);
	RendezVous saveRDV (RendezVous rendezVous);
	Consultation saveConsultation (Consultation consultation);
	
}
