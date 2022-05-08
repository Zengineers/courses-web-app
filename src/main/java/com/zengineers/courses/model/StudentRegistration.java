package com.zengineers.courses.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	private StudentRegistrationId studentRegistrationId = new StudentRegistrationId();
	
	@MapsId("courseId")
//	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@ManyToOne
	@JoinColumn(name="course_id", referencedColumnName = "id")
	private Course course;
	
	@MapsId("studentId")
//	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@ManyToOne
	@JoinColumn(name="student_id", referencedColumnName = "id")
	private Student student;
	
	@Column(name="project_grade")
	private Double projectGrade;
	
	@Column(name="exam_grade")
	private Double examGrade;
	
	@Column(name="total_grade")
	private Double totalGrade;
	
	@Transient
	private boolean violatesConstraintsInDatabase = false;
	
	public StudentRegistration() {}
	
	public StudentRegistration(Long studentId, Long courseId) {
		this.studentRegistrationId = new StudentRegistrationId(studentId, courseId);
	}
	
	public StudentRegistration(Long studentId, Long courseId, Student student, Course course) {
		this.studentRegistrationId = new StudentRegistrationId(studentId, courseId);
		this.student = student;
		this.course = course;
	}
	
	public StudentRegistrationId getStudentRegistrationId() {
		return studentRegistrationId;
	}

	public void setStudentRegistrationId(StudentRegistrationId studentRegistrationId) {
		this.studentRegistrationId = studentRegistrationId;
	}
	
	private void setStudentRegistrationId() {
		studentRegistrationId.setCourseId(course.getId());
		studentRegistrationId.setStudentId(student.getId());
	}
	
	public void updateStudentRegistration(Student student, Course course) {
		this.setStudent(student);
		this.setCourse(course);
		this.setStudentRegistrationId();
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
	
	public boolean violatesConstraintsInDatabase() {
		return violatesConstraintsInDatabase;
	}

	public Double getProjectGrade() {
		return projectGrade;
	}

	public void setProjectGrade(Double projectGrade) {
		this.projectGrade = projectGrade;
	}

	public Double getExamGrade() {
		return examGrade;
	}

	public void setExamGrade(Double examGrade) {
		this.examGrade = examGrade;
	}

	public Double getTotalGrade() {
		return totalGrade;
	}

	public void setTotalGrade(Double totalGrade) {
		this.totalGrade = totalGrade;
	}

	public boolean isViolatesConstraintsInDatabase() {
		return violatesConstraintsInDatabase;
	}
	
	public void setViolatesConstraintsInDatabase(boolean violatesConstraintsInDatabase) {
		this.violatesConstraintsInDatabase = violatesConstraintsInDatabase;
	}
	
	// potential TODO - brainstorming
	// add projectGrade and examGrade weight values
	// (instead of the currently hard-coded ones)
	// which the instructor can specify
	public void computeTotalGrade() {
		if (examGrade == null || projectGrade == null) return;
		if (examGrade.isNaN() || projectGrade.isNaN() ) return;
		this.totalGrade = projectGrade*0.3 + examGrade*0.7;
	}

	/* Debug */
	public void printDetails() {
		String msg = "Course Id: " + studentRegistrationId.getCourseId() + " " +
				"Student Id: " + studentRegistrationId.getStudentId() + "\n";
		System.out.println(msg);
	}
	
}
