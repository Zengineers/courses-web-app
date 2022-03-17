package com.zengineers.courses.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zengineers.courses.model.Instructor;

public interface InstructorDAO extends JpaRepository<Instructor, Long> {
	
	@Query("SELECT inst FROM Instructor inst WHERE inst.username = ?1")
	public Instructor findByUsername(String username);
}
