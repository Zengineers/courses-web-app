package com.zengineers.courses.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zengineers.courses.model.StudentRegistration;

@Repository
public interface StudentRegistrationDAO extends JpaRepository<StudentRegistration, Long>{

}
