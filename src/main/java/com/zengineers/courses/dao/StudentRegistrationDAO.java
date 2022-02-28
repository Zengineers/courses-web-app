package com.zengineers.courses.dao;

import java.util.List;

import com.zengineers.courses.model.StudentRegistration;

public interface StudentRegistrationDAO {

	public List<StudentRegistration> findRegistrationsByCourseId(int courseId);
	public void delete(int studentId);
	public void save(StudentRegistration studentRegistration);
	public void update(StudentRegistration studentRegistration);
}
