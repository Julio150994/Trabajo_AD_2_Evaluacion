package com.segprivado.service;

import com.segprivado.entity.Patient;
import com.segprivado.model.PatientModel;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PatientService {
	
	public abstract List<PatientModel> listAllPatients();
	public abstract Page<Patient> showPaginations(Pageable pagination);
	
	public abstract PatientModel addPatient(PatientModel patientModel);
	public abstract int removePatient(int id);
	public abstract PatientModel updatePatient(PatientModel patientModel);
	public abstract PatientModel findPatient(int id);
	
	public abstract Patient converDozerPatient(PatientModel patientModel);
	public abstract PatientModel converDozerPatient(Patient patientModel);
}
