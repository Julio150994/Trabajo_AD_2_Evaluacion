package com.segprivado.paginations;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.segprivado.entity.Patient;
import org.springframework.stereotype.Repository;


@Repository("paginationPatientRepository")
public interface PaginationPatientRepository extends PagingAndSortingRepository<Patient,Integer> {

}
