package com.zengineers.courses.dao;

import java.util.List;

import com.zengineers.courses.model.StudentRegistration;

public class StudentRegistrationDAOImpl implements StudentRegistrationDAO {
	
	/* TODO which EntityManager do we want? */
	//private EntityManager entityManager;
	
	public StudentRegistrationDAOImpl(/*TODO EntityManager entityManager*/) {
		super();
		// TODO Auto-generated constructor stub
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
