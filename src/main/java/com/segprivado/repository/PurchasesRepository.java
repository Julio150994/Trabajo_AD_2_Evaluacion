package com.segprivado.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.segprivado.entity.Purchase;


@Repository("purchasesRepository")
public interface PurchasesRepository extends JpaRepository<Purchase, Serializable> {

}
