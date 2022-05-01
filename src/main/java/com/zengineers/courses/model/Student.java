package com.zengineers.courses.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	
	@Id
	@Column(name="id", nullable = false)
	private Long id;
	
	@Column(name="full_name", nullable = false, unique = false, length = 55)
	private String fullName;
	
	@Column(name="semester")
	private int semester;
	
	@Column(name="registration_year")
	private int registrationYear;
	
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String full_name) {
		this.fullName = full_name;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getRegistrationYear() {
		return registrationYear;
	}

	public void setRegistrationYear(int registration_year) {
		this.registrationYear = registration_year;
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
	
	/* Debug */
	public void printDetails() {
		String msg = id + " " + 
				fullName + " " +
				semester + " " +
				registrationYear + " " +
				mail + " " + 
				department + "\n";
		System.out.println(msg);
	}

}
