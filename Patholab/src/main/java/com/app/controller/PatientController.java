package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.LoginRequestDto;
import com.app.dto.PatientDto;
import com.app.service.PatientService;
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPatient(@Valid @RequestBody PatientDto patientDto) {
    	try {
    	patientService.addPatient(patientDto);
        return new ResponseEntity<>(new ApiResponse("Patient added successfully"), HttpStatus.CREATED);
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
    }
    @PutMapping("/update/{patientId}")
    public ResponseEntity<?> updatePatient(@PathVariable Long patientId, @RequestBody PatientDto patientdto) {
       try {
    	patientService.updatePatient(patientId, patientdto);
        return new ResponseEntity<>(new ApiResponse("Patient updated successfully"), HttpStatus.OK);
       } catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
    }

    @GetMapping("/view/{patientId}")
    public ResponseEntity<?> viewPatient(@PathVariable Long patientId) {
    	try {
    	PatientDto patientDto = patientService.viewPatient(patientId);
        return new ResponseEntity<>(patientDto, HttpStatus.OK);
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
    }
    @GetMapping
    public ResponseEntity<?> viewAllPatient() {
    	try {
    	List<PatientDto> patients = patientService.viewAllPatients();
        return ResponseEntity.ok(patients);
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
    }
    @PostMapping("/login")
    public ResponseEntity<?> patientLogin(@RequestBody @Valid LoginRequestDto loginRequest) {
       try {
    	boolean isAuthenticated = patientService.authenticatePatient(loginRequest.getEmail(), loginRequest.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok(new ApiResponse("Patient login successful"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
       } catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
    }

}
