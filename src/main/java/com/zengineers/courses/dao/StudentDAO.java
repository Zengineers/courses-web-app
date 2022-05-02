package com.zengineers.courses.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zengineers.courses.model.Student;

@Repository
public interface StudentDAO extends JpaRepository<Student, Long> {
	
	public Student findByRegistrationNumber(int registrationNumber);
	public boolean existsByRegistrationNumber(int registrationNumber);
}
