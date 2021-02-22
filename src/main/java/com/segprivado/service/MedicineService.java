package com.segprivado.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.segprivado.model.MedicineModel;
import com.segprivado.entity.Medicine;
import com.segprivado.entity.Patient;


public interface MedicineService {
	
	public List<MedicineModel> listAllMedicines();
	public abstract Page<Patient> showPaginations(Pageable pagination);
	public MedicineModel addMedicine(MedicineModel medicineModel);
	public int removeMedicine(int id);
	public MedicineModel updateMedicine(MedicineModel medicineModel);
	public MedicineModel findMedicine(int id);
	public abstract Medicine converDozerMedicine(MedicineModel modelMedicine);
	public abstract MedicineModel converDozerMedicine(Medicine medicine);
}
