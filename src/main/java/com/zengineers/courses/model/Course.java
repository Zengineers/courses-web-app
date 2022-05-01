package com.zengineers.courses.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
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
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="instructor_id", referencedColumnName = "id")
	private Instructor instructor;

	// TODO Throws exception 
	// look into: field access strategy
	@OneToMany(targetEntity=StudentRegistration.class, mappedBy="course", fetch=FetchType.EAGER)
	private List<StudentRegistration> studentRegistrations; 

	@Transient
	private boolean codeExistsInDatabase = false;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public boolean isCodeExistsInDatabase() {
		return codeExistsInDatabase;
	}

	public void setCodeExistsInDatabase(boolean codeExistsInDatabase) {
		this.codeExistsInDatabase = codeExistsInDatabase;
	}

	public List<StudentRegistration> getStudentRegistrations() {
		return studentRegistrations;
	}

	public void setStudentRegistrations(List<StudentRegistration> studentRegistrations) {
		this.studentRegistrations = studentRegistrations;
	}
	
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<Student>();
		for (StudentRegistration studentRegistration : studentRegistrations) {
			students.add(studentRegistration.getStudent());
		}
		return students;
	}
	
}
