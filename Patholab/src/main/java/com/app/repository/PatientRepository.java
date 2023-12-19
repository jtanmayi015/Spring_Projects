package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Patient;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	Patient findByEmail(String email);

}
