package com.zengineers.courses.dao;

import java.util.List;

import com.zengineers.courses.model.Course;

public class CourseDAOImpl implements CourseDAO {

	/* TODO which EntityManager do we want? */
	//private EntityManager entityManager;
	
	public CourseDAOImpl(/*TODO EntityManager entityManager*/) {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<Course> findCourseByInstructorLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int courseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Course course) {
		// TODO Auto-generated method stub
		
	}

}
