package com.app.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ReportDto;
import com.app.dto.ViewReportDto;
import com.app.entities.Employee;
import com.app.entities.Patient;
import com.app.entities.Report;
import com.app.entities.Test;
import com.app.repository.EmployeeRepository;
import com.app.repository.PatientRepository;
import com.app.repository.ReportRepository;
import com.app.repository.TestRepository;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private EmployeeRepository employeeRepository;


	@Override
	public void addReport(ReportDto reportDto) {
		Report report = modelMapper.map(reportDto, Report.class);
		Employee employee = employeeRepository.findById(reportDto.getEmp()).orElseThrow(
				() -> new EntityNotFoundException("Employee with empId " + reportDto.getEmp() + " not found"));

		Patient patient = patientRepository.findById(reportDto.getPatient()).orElseThrow(
				() -> new EntityNotFoundException("Patient with patientId " + reportDto.getPatient() + " not found"));

		Test test = testRepository.findById(reportDto.getTestId()).orElseThrow(
				() -> new EntityNotFoundException("Test with testId " + reportDto.getTestId() + " not found"));
		report.setEmp(employee);
		report.addPatient(patient);
		report.setTestId(test);
		report.setCreatedBy(employee.getFirstName()+ " "+employee.getLastName());
		report.setCreatedOn(LocalDate.now());
		report.setModifyBy(employee.getFirstName()+" "+employee.getLastName());
		report.setModifyOn(LocalDate.now());

		reportRepository.save(report);
		
	}


	@Override
	public void updateReport(Long reportId, ReportDto reportDto) {
		Report existingReport = reportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Report with reportId " + reportId + " not found"));
		LocalDate createdOn=existingReport.getCreatedOn();
        // Update fields from the DTO
        modelMapper.map(reportDto, existingReport);

        // Update relationships
        Employee employee = employeeRepository.findById(reportDto.getEmp())
                .orElseThrow(() -> new EntityNotFoundException("Employee with empId " + reportDto.getEmp() + " not found"));
        
        Patient patient = patientRepository.findById(reportDto.getPatient())
                .orElseThrow(() -> new EntityNotFoundException("Patient with patientId " + reportDto.getPatient() + " not found"));
        
        Test test = testRepository.findById(reportDto.getTestId())
                .orElseThrow(() -> new EntityNotFoundException("Test with testId " + reportDto.getTestId() + " not found"));

        existingReport.getEmp().removeReport(existingReport);
        existingReport.getPatients().clear();
//        existingReport.getTestId().removeReport(existingReport);
        
        existingReport.setEmp(employee);
        existingReport.addPatient(patient);
        existingReport.setTestId(test);
        existingReport.setCreatedBy(employee.getFirstName()+" "+employee.getLastName());
        existingReport.setCreatedOn(createdOn);
        existingReport.setModifyBy(employee.getFirstName()+" "+employee.getLastName());
        existingReport.setModifyOn(LocalDate.now());

        reportRepository.save(existingReport);
		
	}
	
	@Override
	public ViewReportDto viewReport(Long reportId,Long patientId) {
		Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
		 Patient patient = patientRepository.findById(patientId)
	                .orElseThrow(() -> new EntityNotFoundException("Patient with patientId " + patientId+ " not found")); 
		 
		ViewReportDto viewReport=modelMapper.map(report, ViewReportDto.class);
		viewReport.setPatientFirstName(patient.getFirstName());
		viewReport.setPatientLastName(patient.getLastName());
		viewReport.setPhoneNo(patient.getPhoneNo());
		viewReport.setEmail(patient.getEmail());
		viewReport.setAddress(patient.getAddress());
		
		return viewReport;
	}
	public void removeReport(Long reportId) {
	    Report report = reportRepository.findById(reportId)
	            .orElseThrow(() -> new EntityNotFoundException("Report with reportId " + reportId + " not found"));

	    reportRepository.delete(report);
	}
	@Override
	public Set<ViewReportDto> viewAllReports(Long patientId) {
//		List<Report> report = reportRepository.findAll();	
//		return report.stream()
//                .map(reports -> modelMapper.map(reports, ViewReportDto.class))
//                .collect(Collectors.toList());
		Patient patient = patientRepository.findById(patientId)
	            .orElseThrow(() -> new EntityNotFoundException("Patient with patientId " + patientId + " not found"));

	    Set<Report> reports = patient.getReports();
	    Set<ViewReportDto> viewReports = new HashSet<>();

	    for (Report report : reports) {
	        ViewReportDto viewReport = modelMapper.map(report, ViewReportDto.class);
	        viewReport.setPatientFirstName(patient.getFirstName());
	        viewReport.setPatientLastName(patient.getLastName());
	        viewReport.setPhoneNo(patient.getPhoneNo());
	        viewReport.setEmail(patient.getEmail());
	        viewReport.setAddress(patient.getAddress());

	        viewReports.add(viewReport);
	    }

	    return viewReports;
	}
	 
	
}
