package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.TestDto;
import com.app.dto.TestPatient;
import com.app.service.TestService;

@RestController
@RequestMapping("/tests")
public class TestController {
	private  TestService testService;
    
    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping
	public ResponseEntity<?> addTest(@RequestBody TestDto testDto) {
		try {
			String test = testService.addTest(testDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(test);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
    
    @GetMapping("/view/{testId}")
    public ResponseEntity<?> viewTest(@PathVariable Long testId) {
    	try {
    	TestDto testDto = testService.viewTest(testId);
        return new ResponseEntity<>(testDto, HttpStatus.OK);
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
    }
   
    @DeleteMapping("/{testId}")
    public ResponseEntity<?> removeTests(@PathVariable Long testId) {
    	try {
        testService.removeTests(testId);
        return new ResponseEntity<>(new ApiResponse("Test deleted successfully"), HttpStatus.CREATED);
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
		
    }
    @GetMapping
    public ResponseEntity<?> getAllTests() {
    	try {
        List<TestDto> tests = testService.getAllTests();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTest(@PathVariable Long id, @RequestBody TestDto testDto) {
        try {
    	boolean updated = testService.updateTest(id, testDto);
        if (updated) {
            return new ResponseEntity<>(new ApiResponse("Test updated successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse("Test not found or couldn't be updated"), HttpStatus.NOT_FOUND);
        }
        } catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
    }
    @PostMapping("/patients")
	public ResponseEntity<?> addPatientToTest(@RequestBody TestPatient testPatient) {
		try {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(testService.assignPatientToTest(testPatient.getPatientId(), testPatient.getTestId()));
		} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
		}
}
