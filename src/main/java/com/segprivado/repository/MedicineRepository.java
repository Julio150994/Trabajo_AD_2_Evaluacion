package com.segprivado.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.segprivado.entity.Medicine;


@Repository("medicineRepository")
public interface MedicineRepository extends JpaRepository<Medicine, Serializable> {
	
}
