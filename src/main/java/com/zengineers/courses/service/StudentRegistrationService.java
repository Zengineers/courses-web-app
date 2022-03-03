package com.zengineers.courses.service;

import java.util.List;

import com.zengineers.courses.model.StudentRegistration;

public interface StudentRegistrationService {
	
	public List<StudentRegistration> findRegistrationsByCourseId(int courseId);
	public void delete(int studentId);
	public void save(StudentRegistration studentRegistration);
	public void update(StudentRegistration studentRegistration);
}
	