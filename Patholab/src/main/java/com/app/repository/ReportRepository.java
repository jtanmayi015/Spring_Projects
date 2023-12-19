package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Patient;
import com.app.entities.Report;
import com.app.entities.Test;
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

	

}
