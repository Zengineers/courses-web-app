package com.zengineers.courses.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	@Column(name="id", nullable = false)
	private Long id;
	
	@Column(name="full_name", nullable = false, unique = false, length = 55)
	private String full_name;
	
	@Column(name="semester")
	private int semester;//st course to exoume string
	
	@Column(name="registration_year")
	private int registration_year;
	
	@Column(name="mail", nullable = true, unique = true, length = 55)
    private String mail;
	
	@Column(name="department", nullable = true, unique = false, length = 55)
    private String department;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getRegistration_year() {
		return registration_year;
	}

	public void setRegistration_year(int registration_year) {
		this.registration_year = registration_year;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	
	
	

}
