package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.PatientDto;
import com.app.entities.Employee;
import com.app.entities.Patient;
import com.app.repository.EmployeeRepository;
import com.app.repository.PatientRepository;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void addPatient(@Valid PatientDto patientDto) {
		Patient patient = modelMapper.map(patientDto, Patient.class);

		if (patientDto.getEmpId() != null) {
			Employee employee = employeeRepository.findById(patientDto.getEmpId()).orElseThrow(
					() -> new EntityNotFoundException("Employee with empId " + patientDto.getEmpId() + " not found"));
			patient.setEmp(employee);
		}
		patient.setCreatedOn(LocalDate.now());
		patient.setCreatedBy(patient.getFirstName()+" "+patient.getLastName());
//		patient.setModifyOn(LocalDate.now());
//		patient.setModifyBy(patient.getPatientFirstName());
		patientRepository.save(patient);

	}

	@Override
	public void updatePatient(Long patientId, PatientDto patientdto) {
		Patient updatePatient = patientRepository.findById(patientId)
				.orElseThrow(() -> new EntityNotFoundException("Patient with patientId " + patientId + " not found"));

		// Update fields from the DTO
		modelMapper.map(patientdto, updatePatient);

		// Update relationships
		Employee employee = employeeRepository.findById(patientdto.getEmpId()).orElseThrow(
				() -> new EntityNotFoundException("Employee with empId " + patientdto.getEmpId() + " not found"));

		updatePatient.getEmp().removePatient(updatePatient);

		updatePatient.setEmp(employee);

		patientRepository.save(updatePatient);

	}

	@Override
	public PatientDto viewPatient(Long patientId) {
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new RuntimeException("patient not found"));

		PatientDto viewPatient = modelMapper.map(patient, PatientDto.class);
		viewPatient.setEmpId(patient.getEmp().getId());
//		viewPatient.setPatientId(patientId);
		return viewPatient;
	}
	@Override
	public List<PatientDto> viewAllPatients() {
		List<Patient> patients = patientRepository.findAll();	
		List<PatientDto> patientDtos = patients.stream()
	            .map(patient -> modelMapper.map(patient, PatientDto.class))
	            .collect(Collectors.toList());

		int index = 0;
	    for (Patient patient : patients) {
	        PatientDto patientDto = patientDtos.get(index);
//	        patientDto.setPatientId(patient.getId());
	        
	        if (patient.getEmp() != null) {
	            patientDto.setEmpId(patient.getEmp().getId());
	        }
	        
	        index++;
	    }

	    return patientDtos;
	}

	@Override
	public boolean authenticatePatient(String email, String password) {
		  Patient patient = patientRepository.findByEmail(email);
	        if (patient != null && patient.getPassword().equals(password)) {
	            return true; // Authentication successful
	        }
	        return false;
		
	}
	
}
