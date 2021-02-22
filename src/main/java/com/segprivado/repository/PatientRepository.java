package com.segprivado.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.segprivado.entity.Patient;


@Repository("patientRepository")
public interface PatientRepository extends JpaRepository<Patient, Serializable> {
	
}
