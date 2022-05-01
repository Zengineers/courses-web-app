package com.zengineers.courses.model.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.zengineers.courses.model.Course;
import com.zengineers.courses.model.Student;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegistrationId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StudentRegistrationId() {}
	
	public StudentRegistrationId(Long studentId, Long courseId) {
		this.studentId = studentId;
		this.courseId = courseId;
	}

	@Column(name = "student_id", nullable = false)
	private Long studentId;
	
	@Column(name = "course_id", nullable = false)
	private Long courseId;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
}
