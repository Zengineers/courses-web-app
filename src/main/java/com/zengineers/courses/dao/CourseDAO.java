package com.zengineers.courses.dao;

import java.util.List;

import com.zengineers.courses.model.Course;

public interface CourseDAO {
 	
 	public List<Course> findCourseByInstructorLogin(String login);
 	public void delete(int courseId);
 	public void save(Course course);
 	public void update(Course course);
}
