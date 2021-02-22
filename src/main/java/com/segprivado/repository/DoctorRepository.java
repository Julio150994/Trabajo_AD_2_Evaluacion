package com.segprivado.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.segprivado.entity.Doctor;
import org.springframework.stereotype.Repository;


@Repository("doctorRepository")
public interface DoctorRepository extends JpaRepository<Doctor, Serializable> {
	
	/*@Query(value="SELECT especialidad FROM medicos ORDER BY especialidad ASC")
	public abstract List<Doctor> findByEspecialidad(String especialidad);*/
}
