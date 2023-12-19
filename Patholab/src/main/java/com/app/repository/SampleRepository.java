package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Sample;
@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {

}
