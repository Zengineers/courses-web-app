package com.zengineers.courses;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.zengineers.courses.model.Course;
import com.zengineers.courses.model.Instructor;
import com.zengineers.courses.model.Student;
import com.zengineers.courses.model.StudentRegistration;
import com.zengineers.courses.model.id.StudentRegistrationId;

public class MockObjectGenerator {

	public static Course createMockCourse(Long id, String code, String name, String syllabus, int year, String semester) {
		Course course = new Course();
		course.setId(id);
		course.setCode(code);
		course.setName(name);
		course.setYear(year);
		course.setSyllabus(syllabus);
		course.setSemester(semester);
		return course;
	}
	
	public static Instructor createMockInstructor(Long id, String username, String password, String mail) {
		Instructor instructor = new Instructor();
		instructor.setId(id);
		instructor.setUsername(username);
		instructor.setPassword(password);
		instructor.setMail(mail);
		return instructor;
	}
	
	public static Student createMockStudent(
			Long id, 
			int registrationNumber, 
			String fullName, 
			int semester, 
			int registrationYear, 
			String mail, 
			String department) {
		Student student = new Student();
		student.setId(id);
		student.setRegistrationNumber(registrationNumber);
		student.setFullName(fullName);
		student.setSemester(semester);
		student.setRegistrationYear(registrationYear);
		student.setMail(mail);
		student.setDepartment(department);
		return student;
	}
	
	public static StudentRegistration createMockStudentRegistration(
			Student student,
			Course course) {
		StudentRegistration studentReg = new StudentRegistration();
		StudentRegistrationId studentRegId = new StudentRegistrationId(student.getId(), course.getId());
		studentReg.setStudentRegistrationId(studentRegId);
		studentReg.setStudent(student);
		studentReg.setCourse(course);
		studentReg.setExamGrade(5.0);
		studentReg.setProjectGrade(5.0);
		return studentReg;
	}
	
	public static MultiValueMap<String, String> createMockCourseForm(String id) {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("id", id);
		multiValueMap.add("code", "MYY000");
		multiValueMap.add("name", "Test Course");
		multiValueMap.add("semester", "Winter");
		multiValueMap.add("syllabus", "Lorem Ipsum");
		multiValueMap.add("year", "4");
		return multiValueMap;
	}
	
	public static MultiValueMap<String, String> createMockStudentRegistrationForm(String id, String registrationNumber) {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("id", id);
		multiValueMap.add("registrationNumber", registrationNumber);
		multiValueMap.add("fullName", "mockName");
		multiValueMap.add("semester", "0");
		multiValueMap.add("registrationYear", "0");
		multiValueMap.add("mail", "mock@mail.com");
		multiValueMap.add("department", "mock dept");
		return multiValueMap;
	}
	
}