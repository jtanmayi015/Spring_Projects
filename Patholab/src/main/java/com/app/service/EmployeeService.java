package com.app.service;

import java.util.List;

import com.app.dto.EmployeeDto;
import com.app.dto.ViewEmployeeDto;

public interface EmployeeService {

	void addEmployee(EmployeeDto employeeDto);

	void updateEmployee(Long employeeId, EmployeeDto employeeDto);

	ViewEmployeeDto viewEmployee(Long employeeId);

	void removeEmployee(Long employeeId);

	List<ViewEmployeeDto> viewAllEmployees();

	boolean authenticateEmployee(String email, String password);
	
}
