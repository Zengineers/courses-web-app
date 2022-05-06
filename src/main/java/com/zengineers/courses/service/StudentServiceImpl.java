package com.zengineers.courses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zengineers.courses.dao.StudentDAO;
import com.zengineers.courses.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDAO studentDAO;
	
	public StudentServiceImpl(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	public StudentServiceImpl() {}
	
	@Override
	public void save(Student student) {
		studentDAO.save(student);
	}

	@Override
	public Student findByRegistrationNumber(int registrationNumber) {
		return studentDAO.findByRegistrationNumber(registrationNumber);
	}

	@Override
	public void delete(Long studentId) {
		studentDAO.deleteById(studentId);
	}

	@Override
	public boolean existsByRegistrationNumber(int registrationNumber) {
		return studentDAO.existsByRegistrationNumber(registrationNumber);
	}

	public Student searchForExistingStudent(Student student) {
		int regNumber = student.getRegistrationNumber();
		boolean exists = existsByRegistrationNumber(regNumber);
		if (exists) {
			// Set student id to match student id of the existing student 
			Student existingStudent = findByRegistrationNumber(regNumber);
			student.setId(existingStudent.getId());
		}
		return student;
	}
	
}
