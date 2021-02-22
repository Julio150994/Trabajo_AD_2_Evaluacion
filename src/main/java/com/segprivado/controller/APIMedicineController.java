package com.segprivado.controller;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.segprivado.model.MedicineModel;
import com.segprivado.service.impl.MedicineImpl;


@RestController
@RequestMapping("/")
public class APIMedicineController {
	private static Log LOG_APIREST = LogFactory.getLog(APIMedicineController.class);
	
	@Autowired
	@Qualifier("medicineImpl")
	private MedicineImpl medicines;
	
	
	@GetMapping("/medicines")
	public ResponseEntity<?> getAllMedicines() {
		List<MedicineModel> listMedicines = medicines.listAllMedicines();
		
		if(listMedicines.isEmpty()) {
			LOG_APIREST.error("Error. Medicines not found (401)");
			return ResponseEntity.notFound().build();
		}
		else {
			LOG_APIREST.info("Show medicines correctly!!");
			return ResponseEntity.ok(medicines);
		}
	}
	
	@GetMapping("/medicines/{id}")
	public ResponseEntity<?> getMedicine(@PathVariable int id) {
		MedicineModel medicine = medicines.findMedicine(id);
		
		if(medicine == null) {
			LOG_APIREST.error("Error. Medicine not found (401)");
			return ResponseEntity.notFound().build();
		}
		else {
			LOG_APIREST.info("Medicine found successfully!!");
			return ResponseEntity.ok(medicines);
		}
	}
	
	@PostMapping("/medicine")
	public ResponseEntity<?> addMedicine(@RequestBody MedicineModel medicineModel) {
		LOG_APIREST.info("Add medicine correctly!!");
		medicines.addMedicine(medicineModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(medicineModel);
	}
}
