package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Bill;
import com.app.entities.Patient;
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
	 List<Bill> findByPatient(Patient patient);
}
