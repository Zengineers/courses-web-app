package com.zengineers.courses.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zengineers.courses.dao.StudentRegistrationDAO;
import com.zengineers.courses.model.StudentRegistration;
import com.zengineers.courses.model.id.StudentRegistrationId;

@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService {

	@Autowired
	StudentRegistrationDAO studentRegistrationDAO;
	
	public StudentRegistrationServiceImpl(StudentRegistrationDAO studentRegistrationDAO) {
		this.studentRegistrationDAO = studentRegistrationDAO;
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
	public StudentRegistration findById(StudentRegistrationId studentRegistrationId) {		
		StudentRegistration studentRegistration = studentRegistrationDAO.findById(studentRegistrationId).get();
		
		if (studentRegistration != null ) {
			return studentRegistration;
		}
		throwRuntimeExceptionStudentRegistrationNotFound(studentRegistrationId);
		return null;
	}

	private void throwRuntimeExceptionStudentRegistrationNotFound(StudentRegistrationId studentRegistrationId) {
		String exceptionMessage = "Did not find student registration with course ID:" + 
				studentRegistrationId.getCourseId() + 
				" and student ID:" + 
				studentRegistrationId.getStudentId();
		throw new RuntimeException(exceptionMessage);
	}

}
