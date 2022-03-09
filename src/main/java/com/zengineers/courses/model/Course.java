package com.zengineers.courses.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

	@Id
	@Column(name="id")
	private long id;
	
	@Column(name="code")
	private String code;
	
	@Column(name="name")
	private String name;
	
	@Column(name="syllabus")
	private String syllabus;
	
	@Column(name="semester")
	private String semester;
	
	@Column(name="year")
	private int year = 0;
	
	//	TODO
	// associate string filed name with int instructor_id in db
	private String instructorName;
	
	// TODO Throws exception 
	// field access strategy
//	private List<StudentRegistration> studentRegistrations; 



	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

//	public String getInstructorName() {
//		return instructorName;
//	}
//
//	public void setInstructorName(String instructorName) {
//		this.instructorName = instructorName;
//	}
//
//	public List<StudentRegistration> getStudentRegistrations() {
//		return studentRegistrations;
//	}
//
//	public void setStudentRegistrations(List<StudentRegistration> studentRegistrations) {
//		this.studentRegistrations = studentRegistrations;
//	}
	
	
}
