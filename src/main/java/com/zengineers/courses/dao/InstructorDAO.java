package com.zengineers.courses.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zengineers.courses.model.Instructor;

public interface InstructorDAO extends JpaRepository<Instructor, Long> {

}
