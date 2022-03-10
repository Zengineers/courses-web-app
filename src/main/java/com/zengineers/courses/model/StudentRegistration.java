package com.zengineers.courses.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="student_registrations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegistration {

	// TODO 
	// This is not the correct PK
	// Look into how to have two FKs as PK on hibernate jpa
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
}
