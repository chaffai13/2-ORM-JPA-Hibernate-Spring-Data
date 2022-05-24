package com.chaffai.ma.services;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.chaffai.ma.entities.Consultation;
import com.chaffai.ma.entities.Medecin;
import com.chaffai.ma.entities.Patient;
import com.chaffai.ma.entities.RendezVous;
import com.chaffai.ma.repositories.ConsultationRepository;
import com.chaffai.ma.repositories.MedecinRepository;
import com.chaffai.ma.repositories.PatientRepository;
import com.chaffai.ma.repositories.RendezVousRepository;

@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {

	private PatientRepository patientRepository;
	private MedecinRepository medecinRepository;
	private RendezVousRepository rendezVousRepository;
	private ConsultationRepository consultationRepository;

	public HospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository,
			RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
		this.patientRepository = patientRepository;
		this.medecinRepository = medecinRepository;
		this.rendezVousRepository = rendezVousRepository;
		this.consultationRepository = consultationRepository;
	}

	@Override
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public Medecin saveMedecin(Medecin medecin) {
		return medecinRepository.save(medecin);
	}

	@Override
	public RendezVous saveRDV(RendezVous rendezVous) {
		
		//UUID.randomUUID() => génere une chaine de caractere aléatoire qui est unique car elle dépend de la date systeme
		rendezVous.setId(UUID.randomUUID().toString());
		return rendezVousRepository.save(rendezVous);
	}

	@Override
	public Consultation saveConsultation(Consultation consultation) {
		return consultationRepository.save(consultation);
	}

}
