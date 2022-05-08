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
	private StudentRegistrationDAO studentRegistrationDAO;
	
	public StudentRegistrationServiceImpl(StudentRegistrationDAO studentRegistrationDAO) {
		this.studentRegistrationDAO = studentRegistrationDAO;
	}
	
	@Override
	public List<StudentRegistration> findRegistrationsByCourseId(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(StudentRegistrationId studentRegistrationId) {
		studentRegistrationDAO.deleteById(studentRegistrationId);
	}

	@Override
	public void save(StudentRegistration studentRegistration) {
		studentRegistrationDAO.save(studentRegistration);
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

	@Override
	public boolean existsByStudentId(Long studentId) {
		return studentRegistrationDAO.existsByStudentId(studentId);
	}

}
