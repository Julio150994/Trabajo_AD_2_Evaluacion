package com.segprivado.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.segprivado.entity.Patient;
import com.segprivado.model.PatientModel;
import com.segprivado.paginations.PaginationPatientRepository;
import com.segprivado.repository.PatientRepository;
import com.segprivado.service.PatientService;
import com.segprivado.uploads.StorageService;


@Service("patientImpl")
public class PatientImpl implements PatientService {
	
	@Autowired
	@Qualifier("patientRepository")
	private PatientRepository patientRepository;
	
	@Autowired
	@Qualifier("paginationPatientRepository")
	private PaginationPatientRepository paginationRepository;
	
	@Autowired
	StorageService storageService;
	
	@Autowired
	private DozerBeanMapper dozerApp;
	
	@Override
	public List<PatientModel> listAllPatients() {
		return patientRepository.findAll().stream().map(p->converDozerPatient(p)).collect(Collectors.toList());
	}

	@Override
	public PatientModel addPatient(PatientModel modelPaciente) {
		return dozerApp.map(patientRepository.save(converDozerPatient(modelPaciente)),PatientModel.class);
	}

	@Override
	public int removePatient(int id) {
		Patient paciente = patientRepository.findById(id).orElse(null);
		if(paciente.getFoto() != null)
			storageService.delete(paciente.getFoto());
		patientRepository.deleteById(id);
		return 0;
	}

	@Override
	public PatientModel updatePatient(PatientModel modelPatient) {
		return dozerApp.map(patientRepository.save(converDozerPatient(modelPatient)),PatientModel.class);
	}

	@Override
	public PatientModel findPatient(int id) {
		return converDozerPatient(patientRepository.findById(id).orElse(null));
	}

	@Override
	public Patient converDozerPatient(PatientModel modelPaciente) {
		return dozerApp.map(modelPaciente, Patient.class);
	}

	@Override
	public PatientModel converDozerPatient(Patient paciente) {
		return dozerApp.map(paciente, PatientModel.class);
	}
	
	@Override
	public Page<Patient> showPaginations(Pageable pagination) {
		return paginationRepository.findAll(pagination);
	}
}
