package com.app.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.SampleDto;
import com.app.entities.Employee;
import com.app.entities.Patient;
import com.app.entities.Sample;
import com.app.entities.Test;
import com.app.repository.EmployeeRepository;
import com.app.repository.PatientRepository;
import com.app.repository.SampleRepository;
import com.app.repository.TestRepository;

@Service
@Transactional

public class SampleServiceImpl implements SampleService {
	@Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void addSample(SampleDto sampleDto) {
        Sample sample = modelMapper.map(sampleDto, Sample.class);
        Employee employee = employeeRepository.findById(sampleDto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee with employeeId " + sampleDto.getEmployeeId() + " not found"));

        Patient patient = patientRepository.findById(sampleDto.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient with patientId " + sampleDto.getPatientId() + " not found"));

        Test test = testRepository.findById(sampleDto.getTestId())
                .orElseThrow(() -> new EntityNotFoundException("Test with testId " + sampleDto.getTestId() + " not found"));

        sample.setEmployeeId(employee);
        sample.setPatientId(patient);
        sample.setTestId(test);

        sampleRepository.save(sample);
    }

    @Override
    public void updateSample(Long sampleId, SampleDto sampleDto) {
        Sample existingSample = sampleRepository.findById(sampleId)
                .orElseThrow(() -> new EntityNotFoundException("Sample with sampleId " + sampleId + " not found"));

        // Update fields from the DTO
        modelMapper.map(sampleDto, existingSample);

        // Update relationships
        Employee employee = employeeRepository.findById(sampleDto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee with employeeId " + sampleDto.getEmployeeId() + " not found"));

        Patient patient = patientRepository.findById(sampleDto.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient with patientId " + sampleDto.getPatientId() + " not found"));

        Test test = testRepository.findById(sampleDto.getTestId())
                .orElseThrow(() -> new EntityNotFoundException("Test with testId " + sampleDto.getTestId() + " not found"));

        existingSample.setEmployeeId(employee);
        existingSample.setPatientId(patient);
        existingSample.setTestId(test);

        sampleRepository.save(existingSample);
    }

    @Override
    public SampleDto viewSample(Long sampleId) {
        Sample sample = sampleRepository.findById(sampleId)
                .orElseThrow(() -> new EntityNotFoundException("Sample with sampleId " + sampleId + " not found"));

        SampleDto viewSample = modelMapper.map(sample, SampleDto.class);
        viewSample.setEmployeeId(sample.getEmployeeId().getId());
        viewSample.setPatientId(sample.getPatientId().getId());
        viewSample.setTestId(sample.getTestId().getId());

        return viewSample;
    }
   
    @Override
    public void removeSample(Long sampleId) {
        Sample sample = sampleRepository.findById(sampleId)
                .orElseThrow(() -> new EntityNotFoundException("Sample with sampleId " + sampleId + " not found"));

        sampleRepository.delete(sample);
    }
}
