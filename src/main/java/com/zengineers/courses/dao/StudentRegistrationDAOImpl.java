package com.zengineers.courses.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.zengineers.courses.model.StudentRegistration;

public class StudentRegistrationDAOImpl implements StudentRegistrationDAO {
	
	private EntityManager entityManager;
	
	public StudentRegistrationDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<StudentRegistration> findRegistrationsByCourseId(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int studentId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(StudentRegistration studentRegistration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(StudentRegistration studentRegistration) {
		// TODO Auto-generated method stub
		
	}

}
