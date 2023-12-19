package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Test;
@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

}
