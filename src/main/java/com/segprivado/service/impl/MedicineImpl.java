package com.segprivado.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.segprivado.entity.Medicine;
import com.segprivado.entity.Patient;
import com.segprivado.model.MedicineModel;
import com.segprivado.paginations.PaginationPatientRepository;
import com.segprivado.repository.MedicineRepository;
import com.segprivado.service.MedicineService;


@Service("medicineImpl")
public class MedicineImpl implements MedicineService {
	
	@Autowired
	@Qualifier("medicineRepository")
	private MedicineRepository medicamentos;
	
	/* Para hacer la parte de paginaciones */
	@Autowired
	@Qualifier("paginationPatientRepository")
	private PaginationPatientRepository paginations;
	
	@Autowired
	private DozerBeanMapper dozerApp;
	
	@Override
	public List<MedicineModel> listAllMedicines() {
		return medicamentos.findAll().stream().map(p->converDozerMedicine(p)).collect(Collectors.toList());
	}
	
	@Override
	public Page<Patient> showPaginations(Pageable pagination) {
		return paginations.findAll(pagination);
	}
	
	@Override
	public MedicineModel findMedicine(int id) {
		return converDozerMedicine(medicamentos.findById(id).orElse(null));
	}

	@Override
	public MedicineModel addMedicine(MedicineModel modelMedicine) {
		return dozerApp.map(medicamentos.save(converDozerMedicine(modelMedicine)), MedicineModel.class);
	}

	@Override
	public int removeMedicine(int id) {
		medicamentos.deleteById(id);
		return 0;
	}

	@Override
	public MedicineModel updateMedicine(MedicineModel medicineModel) {
		return dozerApp.map(medicamentos.save(converDozerMedicine(medicineModel)), MedicineModel.class);
	}
	
	@Override
	public Medicine converDozerMedicine(MedicineModel medicineModel) {
		return dozerApp.map(medicineModel,Medicine.class);
	}

	@Override
	public MedicineModel converDozerMedicine(Medicine medicine) {
		return dozerApp.map(medicine,MedicineModel.class);
	}
}
