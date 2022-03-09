package com.zengineers.courses.service;

import java.util.List;

import com.zengineers.courses.model.Course;

public interface CourseService {

	public List<Course> findCourseByInstructorLogin(String login);
	public List<Course> findAll();
	public void delete(int courseId);
 	public void save(Course course);
 	public void update(Course course);
}
