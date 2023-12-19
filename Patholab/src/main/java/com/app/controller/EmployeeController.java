package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.EmployeeDto;
import com.app.dto.LoginRequestDto;
import com.app.dto.ViewEmployeeDto;
import com.app.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {


	@Autowired
    private EmployeeService employeeService;
	
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto employeeDto) {
    	try {
        employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(new ApiResponse("Employee added successfully"), HttpStatus.CREATED);
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
    }
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDto employeeDto) {
       try {
    	employeeService.updateEmployee(employeeId, employeeDto);
        return new ResponseEntity<>(new ApiResponse("Employee updated successfully"), HttpStatus.OK);
       } catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
    }
    @GetMapping("/view/{employeeId}")
    public ResponseEntity<?> viewEmployee(@PathVariable Long employeeId) {
    	try {
    	ViewEmployeeDto employeeDto = employeeService.viewEmployee(employeeId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
    }
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> removeBill(@PathVariable Long employeeId) {
    	try {
        employeeService.removeEmployee(employeeId);
        return ResponseEntity.ok(new ApiResponse("Employee removed successfully"));
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
    }
    @GetMapping
    public ResponseEntity<?> viewAllEmployee() {
    	try {
    	List<ViewEmployeeDto> employees = employeeService.viewAllEmployees();
        return ResponseEntity.ok(employees);
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
    }
    @PostMapping("/login")
    public ResponseEntity<?> employeeLogin(@RequestBody @Valid LoginRequestDto loginRequest) {
    	try {
        boolean isAuthenticated = employeeService.authenticateEmployee(loginRequest.getEmail(), loginRequest.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok(new ApiResponse("Employee login successful"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
    }
}
