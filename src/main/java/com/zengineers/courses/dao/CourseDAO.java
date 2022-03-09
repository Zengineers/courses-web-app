package com.zengineers.courses.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zengineers.courses.model.Course;

@Repository
public interface CourseDAO extends JpaRepository<Course, Long>{
 	
// 	public List<Course> findCourseByInstructorLogin(String login);
// 	public void delete(int courseId);
// 	public void save(Course course);
// 	public void update(Course course);
}
