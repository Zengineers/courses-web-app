package com.zengineers.courses;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.zengineers.courses.model.Course;
import com.zengineers.courses.model.Instructor;

public class MockObjectGenerator {

	public static Course createMockCourse(Long id, String code, String name, String syllabus, int year, String semester) {
		Course course = new Course();
		course.setId(1L);
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
}