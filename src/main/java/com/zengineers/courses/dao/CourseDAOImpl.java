package com.zengineers.courses.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.zengineers.courses.model.Course;

public class CourseDAOImpl implements CourseDAO {

	private EntityManager entityManager;
	
	public CourseDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
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
