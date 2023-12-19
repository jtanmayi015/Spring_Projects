package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entity.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    // ...
}
