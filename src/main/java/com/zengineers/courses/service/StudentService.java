package com.zengineers.courses.service;

import com.zengineers.courses.model.Student;

public interface StudentService {
	
	public void save(Student student);
	public void delete(Long studentId);
	public Student findByRegistrationNumber(int registrationNumber);
	public boolean existsByRegistrationNumber(int registrationNumber);
	public Student searchForExistingStudent(Student student);
	
}
