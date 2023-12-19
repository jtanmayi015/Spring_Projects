package com.cdac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.cdac.entity.JobApplication;
import com.cdac.exception.ResourceNotFoundException;
import com.cdac.repository.JobApplicationRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @GetMapping
    public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
    }

    @PostMapping
    public JobApplication createJobApplication(@RequestBody JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getJobApplicationById(@PathVariable long id) {
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + id));
        return ResponseEntity.ok(jobApplication);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> updateJobApplication(@PathVariable long id, @RequestBody JobApplication details) {
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + id));

        jobApplication.setName(details.getName());
        jobApplication.setEmail(details.getEmail());
        jobApplication.setWebsite(details.getWebsite());
        jobApplication.setCoverLetter(details.getCoverLetter());

        

        JobApplication updatedJobApplication = jobApplicationRepository.save(jobApplication);
        return ResponseEntity.ok(updatedJobApplication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long id) {
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + id));

        jobApplicationRepository.delete(jobApplication);
        return ResponseEntity.noContent().build();
    }
}
