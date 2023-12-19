package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.TestDto;

public interface TestService {

	String addTest(TestDto testDto);

	void removeTests(Long testId);

	List<TestDto> getAllTests();

	boolean updateTest(Long id, TestDto testDto);

	TestDto viewTest(Long testId);

	ApiResponse assignPatientToTest(Long patientId, Long testId);

}
