package com.app.service;

import java.util.List;

import javax.validation.Valid;

import com.app.dto.PatientDto;

public interface PatientService {

	void addPatient(@Valid PatientDto patientDto);

	void updatePatient(Long patientId, PatientDto patientdto);

	PatientDto viewPatient(Long patientId);
	List<PatientDto> viewAllPatients();

	boolean authenticatePatient(String email, String password);

}
