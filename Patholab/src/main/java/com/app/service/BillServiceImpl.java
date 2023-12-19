package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.BillDto;
import com.app.dto.TotalBillDto;
import com.app.dto.ViewBillDto;
import com.app.entities.Bill;
import com.app.entities.Employee;
import com.app.entities.Patient;
import com.app.entities.Test;
import com.app.repository.BillRepository;
import com.app.repository.EmployeeRepository;
import com.app.repository.PatientRepository;
import com.app.repository.TestRepository;

@Service
@Transactional
public class BillServiceImpl implements BillService {
	@Autowired
	private BillRepository billRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void addBill(BillDto billDto) {
		Bill bill = modelMapper.map(billDto, Bill.class);
		Employee employee = employeeRepository.findById(billDto.getEmpId()).orElseThrow(
				() -> new EntityNotFoundException("Employee with empId " + billDto.getEmpId() + " not found"));

		Patient patient = patientRepository.findById(billDto.getPatientId()).orElseThrow(
				() -> new EntityNotFoundException("Patient with patientId " + billDto.getPatientId() + " not found"));

		Test test = testRepository.findById(billDto.getTestId()).orElseThrow(
				() -> new EntityNotFoundException("Test with testId " + billDto.getTestId() + " not found"));
		  Set<Test> patientTests = patient.getTest();
		  double totalTestPrice = patientTests.stream()
		            .mapToDouble(tests -> tests.getTestPrice())
		            .sum(); 
		bill.setEmps(employee);
		bill.setPatient(patient);
		bill.setTests(test);
		bill.setTotalTestPrice(totalTestPrice);
		bill.setCreatedBy(patient.getFirstName()+" "+patient.getLastName());
		bill.setModifyBy(patient.getFirstName()+" "+patient.getLastName());
		bill.setModifyOn(LocalDate.now());
		
		billRepository.save(bill);
	}

	@Override
	public void updateBill(Long billId, BillDto billDto) {
		 Bill existingBill = billRepository.findById(billId)
	                .orElseThrow(() -> new EntityNotFoundException("Bill with billId " + billId + " not found"));

	        // Update fields from the DTO
	        modelMapper.map(billDto, existingBill);

	        // Update relationships
	        Employee employee = employeeRepository.findById(billDto.getEmpId())
	                .orElseThrow(() -> new EntityNotFoundException("Employee with empId " + billDto.getEmpId() + " not found"));
	        
	        Patient patient = patientRepository.findById(billDto.getPatientId())
	                .orElseThrow(() -> new EntityNotFoundException("Patient with patientId " + billDto.getPatientId() + " not found"));
	        
	        Test test = testRepository.findById(billDto.getTestId())
	                .orElseThrow(() -> new EntityNotFoundException("Test with testId " + billDto.getTestId() + " not found"));

	        existingBill.getEmps().removeBill(existingBill);;
	        existingBill.getPatient().removeBill(existingBill);
	        existingBill.getTests().removeBill(existingBill);
	        
	        existingBill.setEmps(employee);
	        existingBill.setPatient(patient);
	        existingBill.setTests(test);
	        existingBill.setCreatedBy(patient.getFirstName()+" "+patient.getLastName());
	        existingBill.setModifyBy(patient.getFirstName()+" "+patient.getLastName());
	        existingBill.setModifyOn(LocalDate.now());

	        billRepository.save(existingBill);
	    
	}

	@Override
	public BillDto viewBill(Long billId) {
		Bill bill = billRepository.findById(billId).orElseThrow(() -> new RuntimeException("Bill not found"));
		 	BillDto viewBill=modelMapper.map(bill, BillDto.class);
	
		viewBill.setEmpId(bill.getEmps().getId());
		viewBill.setPatientId(bill.getPatient().getId());
		viewBill.setTestId(bill.getTests().getId());
		viewBill.setTotalPrice(bill.getTotalTestPrice());
		
		return viewBill;
	}
	public void removeBill(Long billId) {
	    Bill bill = billRepository.findById(billId)
	            .orElseThrow(() -> new EntityNotFoundException("Bill with billId " + billId + " not found"));

	    billRepository.delete(bill);
	}

	@Override
	public TotalBillDto viewTotalBill(Long patientId) {
		 Patient patient = patientRepository.findById(patientId)
		            .orElseThrow(() -> new EntityNotFoundException("Patient with patientId " + patientId + " not found"));

		    List<Bill> bills = billRepository.findByPatient(patient);
		    
		    double totalBillPrice = bills.stream()
		            .mapToDouble(Bill::getTotalTestPrice)
		            .sum();

		    List<ViewBillDto> billDtos = bills.stream()
		            .map(bill -> {
		            	ViewBillDto newbillDto = modelMapper.map(bill, ViewBillDto.class);
		            	newbillDto.setTestName(bill.getTests().getTestName());
		            	newbillDto.setTestprice(bill.getTests().getTestPrice());
		                return newbillDto;
		            })
		            .collect(Collectors.toList());

		    TotalBillDto billDto = new TotalBillDto();
		    
		    billDto.setPatientId(patientId);
		    billDto.setDate(LocalDate.now());
		    billDto.setPatientFirstName(patient.getFirstName());
		    billDto.setPatientLastName(patient.getLastName());
		    billDto.setAddress(patient.getAddress());
		    billDto.setEmail(patient.getEmail());
		    billDto.setPhoneNo(patient.getPhoneNo());
		    billDto.setTotalPrice(totalBillPrice);
		    billDto.setBills(billDtos);
		    
		    return billDto;
	
	}

}
