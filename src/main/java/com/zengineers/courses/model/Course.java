package com.zengineers.courses.model;

import java.util.List;

public class Course {

	private List<StudentRegistration> studentRegistrations; 
	private String id;
	private String name;
	private String syllabus;
	private String semester;
	private String instructorName;
	private int year;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
