package com.zengineers.courses.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zengineers.courses.model.Course;

@Repository
public interface CourseDAO extends JpaRepository<Course, Long>{

}
