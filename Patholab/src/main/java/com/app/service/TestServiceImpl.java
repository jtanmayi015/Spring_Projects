package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_excceptions.ResourceNotFoundException;
import com.app.dto.ApiResponse;
import com.app.dto.TestDto;
import com.app.entities.Patient;
import com.app.entities.Test;
import com.app.repository.PatientRepository;
import com.app.repository.TestRepository;
@Service
@Transactional
public class TestServiceImpl implements TestService {
	@Autowired
	  private TestRepository testRepo;
	@Autowired
	  private PatientRepository patientRepo;
	@Autowired
	private ModelMapper mapper;
	@Override
	public String addTest(TestDto testDto) {
		
		Test test = mapper.map(testDto, Test.class);
		
		// You can perform any additional operations or validations here

		// Save the test to the repository
		test.setCreatedOn(LocalDate.now());
		test.setModifyOn(LocalDate.now());
		testRepo.save(test);

		return test.getTestName() + " Added Successfully!";
	}
	 @Override
		public TestDto viewTest(Long testId) {
			Test test = testRepo.findById(testId).orElseThrow(() -> new RuntimeException("Test not found"));
			LocalDate createdOn=test.getCreatedOn();
			TestDto viewTest=mapper.map(test, TestDto.class);
			viewTest.setCreatedOn(createdOn);
			viewTest.setModifyOn(LocalDate.now());
			
			return viewTest;
		}
	 @Override
	    public void removeTests(Long testId) {
	        testRepo.deleteById(testId);
	    }
	 @Override
	    public List<TestDto> getAllTests() {
	        List<Test> tests = testRepo.findAll();
	        return tests.stream()
	                .map(test -> mapper.map(test, TestDto.class))
	                .collect(Collectors.toList());
	 }
	 @Override
	    public boolean updateTest(Long id, TestDto testDto) {
	        Optional<Test> optionalTest = testRepo.findById(id);
	        if (optionalTest.isPresent()) {
	            Test test = optionalTest.get();
	            // Update the test properties from testDto
	            test.setTestName(testDto.getTestName());
	            test.setTestPrice(testDto.getTestPrice());
	            // Save the updated test
	            testRepo.save(test);
	            return true;
	        }
	        return false;
	    }
	@Override
	public ApiResponse assignPatientToTest(Long patientId, Long testId) {
		Test test = testRepo.findById(testId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Test ID!!!!"));
		Patient patient = patientRepo.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Invalid Emp ID!!!!"));
		test.addPatient(patient);// establish bi dir relationship
		return new ApiResponse("Patient" + patient.getFirstName() + " added to Test " + test.getTestName());

	}
}
