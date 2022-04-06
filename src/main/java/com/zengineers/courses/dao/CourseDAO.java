package com.zengineers.courses.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zengineers.courses.model.Course;

@Repository
public interface CourseDAO extends JpaRepository<Course, Long>{
	
	@Query("SELECT co FROM Course co WHERE co.instructor.id = ?1")
	public List<Course> findCoursesByInstructorId(Long instructorId);
}
