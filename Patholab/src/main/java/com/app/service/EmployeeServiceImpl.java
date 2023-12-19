package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_excceptions.ResourceNotFoundException;
import com.app.dto.EmployeeDto;
import com.app.dto.ViewEmployeeDto;
import com.app.entities.Admin;
import com.app.entities.Employee;
import com.app.repository.AdminRepository;
import com.app.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addEmployee(EmployeeDto employeeDto) {
    	 employeeDto.setAdminId(1L);
        Employee employee = modelMapper.map(employeeDto, Employee.class);

        // Map Admin by adminId
        Admin admin = adminRepository.findById(employeeDto.getAdminId())
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + employeeDto.getAdminId()));
        employee.setAdminId(admin);
       

        employeeRepository.save(employee);
    }
    
    @Override
    public void updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        // Update employee properties
        modelMapper.map(employeeDto, employee);

        // Update Admin if adminId changes
        if (!employee.getAdminId().getId().equals(employeeDto.getAdminId())) {
            Admin admin = adminRepository.findById(employeeDto.getAdminId())
                    .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + employeeDto.getAdminId()));
            employee.setAdminId(admin);
        }
        

        employeeRepository.save(employee);
    }
    @Override
    public ViewEmployeeDto viewEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setAddress(employee.getAddress());
//        employeeDto.setAdminId(1L);
//        employeeDto.setDateOfBirth(employee.getDateOfBirth());
//        employeeDto.setEmail(employee.getEmail());
//        employeeDto.setFirstName(employee.getFirstName());
//        employeeDto.setLastName(employee.getLastName());
//        employeeDto.setGender(employee.getGender());
//        employeeDto.set;
        ViewEmployeeDto emp=modelMapper.map(employee, ViewEmployeeDto.class);
//    	emp.setAdminId(employee.getAdminId().getId());
        return emp;
    }
  


	@Override
	public void removeEmployee(Long employeeId) {
		  Employee employee = employeeRepository.findById(employeeId)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
	       
		  employeeRepository.delete(employee);
		
	}
	@Override
	public List<ViewEmployeeDto> viewAllEmployees() {
		List<Employee> employee = employeeRepository.findAll();	

		return employee.stream()
                .map(employees -> modelMapper.map(employees, ViewEmployeeDto.class))
                .collect(Collectors.toList());
	}
	@Override
    public boolean authenticateEmployee(String username, String password) {
        Employee employee = employeeRepository.findByEmail(username);
        if (employee != null && employee.getPassword().equals(password)) {
            return true; // Authentication successful
        }
        return false; // Authentication failed
    }

}
