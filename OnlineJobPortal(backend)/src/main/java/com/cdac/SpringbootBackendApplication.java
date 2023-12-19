package com.cdac;

//import net.javaguides.springboot.model.Employee;
//import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cdac.entity.JobApplication;
import com.cdac.repository.JobApplicationRepository;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBackendApplication.class, args);
    }

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Override
    public void run(String... args) throws Exception {
    	JobApplication jobApplication = new JobApplication();
    	jobApplication.setName("mrunu");
    	jobApplication.setEmail("mrunu@gmail.com");
    	jobApplication.setWebsite("www.mrunu.com");
    	jobApplication.setCoverLetter("mrunu salunke"); 
    	

    	jobApplicationRepository.save(jobApplication);

        
    }
}
