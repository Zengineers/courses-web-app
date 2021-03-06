package com.zengineers.courses.service;

import java.util.List;

import com.zengineers.courses.model.Course;

public interface CourseService {

	public List<Course> findCoursesByInstructorId(Long instructorId);
	public List<Course> findAll();
	public void delete(Long courseId);
 	public void save(Course course);
 	public Course findById(Long courseId);
}
