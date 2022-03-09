package com.zengineers.courses.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zengineers.courses.model.Course;

@Repository("CourseDAOImpl")
public class CourseDAOImpl implements CourseDAO {

//	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	public CourseDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public CourseDAOImpl() {
		
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
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
		System.out.println("> CourseDAO");
		
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(course);
	}

	@Override
	public void update(Course course) {
		// TODO Auto-generated method stub
		
	}

}
