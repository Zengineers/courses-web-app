package com.zengineers.courses.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.zengineers.courses.model.id.StudentRegistrationId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="student_registrations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegistration {

	@EmbeddedId
	private StudentRegistrationId studentRegistrationId;
	
	@MapsId("courseId")
	@ManyToOne
	private Course course;
	
	@MapsId("studentId")
	@ManyToOne
	private Student student;
	

	public StudentRegistrationId getStudentRegistrationId() {
		return studentRegistrationId;
	}

	public void setStudentRegistrationId(StudentRegistrationId studentRegistrationId) {
		this.studentRegistrationId = studentRegistrationId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	/* Debug */
	public void printDetails() {
		String msg = "Course Id: " + studentRegistrationId.getCourseId() + " " +
				"Student Id: " + studentRegistrationId.getStudentId() + "\n";
		System.out.println(msg);
	}
	
}
