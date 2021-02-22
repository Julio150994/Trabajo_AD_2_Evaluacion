package com.segprivado.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.segprivado.entity.Doctor;
import com.segprivado.entity.Patient;
import com.segprivado.model.DoctorModel;
import com.segprivado.paginations.PaginationPatientRepository;
import com.segprivado.repository.DoctorRepository;
import com.segprivado.service.DoctorService;


@Service("doctorImpl")
public class DoctorImpl implements DoctorService {
	
	@Autowired
	@Qualifier("doctorRepository")
	private DoctorRepository medicos;
	
	/* Para hacer la parte de paginaciones */
	@Autowired
	@Qualifier("paginationPatientRepository")
	private PaginationPatientRepository paginations;
	
	@Autowired
	private DozerBeanMapper dozerApp;
	
	
	@Override
	public List<DoctorModel> listAllDoctors() {
		return medicos.findAll().stream().map(doc->convertoDoctor(doc)).collect(Collectors.toList());
	}

	@Override
	public Page<Patient> showPaginations(Pageable pagination) {
		return paginations.findAll(pagination);
	}
	
	/*---------Método del servicio para los visitantes------------*/
	@Override
	public List<DoctorModel>  listAllByEspecialidad() {
		return medicos.findAll().stream().filter(doc -> doc.getEspecialidad() != null).map(doc->convertoDoctor(doc)).collect(Collectors.toList());
	}
	
	@Override
	public DoctorModel findDoctor(int id) {
		return convertoDoctor(medicos.findById(id).orElse(null));
	}

	@Override
	public DoctorModel addDoctor(DoctorModel doctorModel) {
		return dozerApp.map(medicos.save(convertoDoctor(doctorModel)),DoctorModel.class);
	}

	@Override
	public int removeDoctor(int id) {
		medicos.deleteById(id);
		return 0;
	}

	@Override
	public DoctorModel updateDoctor(DoctorModel doctorModel) {
		return dozerApp.map(medicos.save(convertoDoctor(doctorModel)),DoctorModel.class);
	}

	@Override
	public Doctor convertoDoctor(DoctorModel doctorModel) {
		return dozerApp.map(doctorModel,Doctor.class);
	}

	@Override
	public DoctorModel convertoDoctor(Doctor doctor) {
		return dozerApp.map(doctor,DoctorModel.class);
	}
}
