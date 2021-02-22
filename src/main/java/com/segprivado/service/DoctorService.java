package com.segprivado.service;

import com.segprivado.entity.Doctor;
import com.segprivado.entity.Patient;
import com.segprivado.model.DoctorModel;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DoctorService {
	
	public abstract List<DoctorModel> listAllDoctors();
	public abstract List<DoctorModel> listAllByEspecialidad();
	
	public abstract Page<Patient> showPaginations(Pageable pagination);
	public abstract DoctorModel addDoctor(DoctorModel doctorModel);
	public abstract int removeDoctor(int id);
	public abstract DoctorModel updateDoctor(DoctorModel doctorModel);
	public abstract DoctorModel findDoctor(int id);
	public abstract Doctor convertoDoctor(DoctorModel doctorModel);
	public abstract DoctorModel convertoDoctor(Doctor doctor);
}
