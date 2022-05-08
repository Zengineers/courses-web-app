package com.zengineers.courses.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zengineers.courses.model.Course;
import com.zengineers.courses.model.Student;
import com.zengineers.courses.model.StudentRegistration;
import com.zengineers.courses.model.id.StudentRegistrationId;
import com.zengineers.courses.service.CourseService;
import com.zengineers.courses.service.StudentRegistrationService;
import com.zengineers.courses.service.StudentService;

@Controller
public class StudentGradesController {

	private StudentRegistrationService studentRegistrationService;
	private CourseService courseService;
	private StudentService studentService;
	
	public StudentGradesController(
			StudentRegistrationService studentRegistrationService,
			CourseService courseService,
			StudentService studentService) {
		super();
		this.studentRegistrationService = studentRegistrationService;
		this.courseService = courseService;
		this.studentService = studentService;
	}
	
	@GetMapping("/student-grades")
	public String listStudentGrades(@RequestParam("courseId") Long courseId, Model model) {
		// Add course to model (to display course code on header)
		Course course = courseService.findById(courseId);
		model.addAttribute("course", course);
		
		// Add student registrations to model
		List<StudentRegistration> studentRegistrations = course.getStudentRegistrations();
		model.addAttribute("studentRegistrations", studentRegistrations);
				
		return "student-grades";
	}
	
	@GetMapping("/student-grades/get-total-grades")
	public String computeTotalGrades(
			@RequestParam("courseId") Long courseId,
			Model model) {
		Course course = courseService.findById(courseId);
		
		for (StudentRegistration studentReg : course.getStudentRegistrations()) {
			studentReg.computeTotalGrade();
			studentRegistrationService.save(studentReg);
		}
		
		return "redirect:/student-grades?courseId=" + courseId;
	}
	
	@GetMapping("/student-grades/update")
	public String updateStudentGrades(
			@RequestParam("courseId") Long courseId, 
			@RequestParam("studentId") Long studentId,
			Model model) {	
		
		// Find the student registration that was selected for update and add it to model
		StudentRegistrationId studentRegId = new StudentRegistrationId(studentId, courseId);
		StudentRegistration studentReg = studentRegistrationService.findById(studentRegId);
		model.addAttribute("studentRegistrationForm", studentReg);
		
		// Add course id and student id to model - we need them as params
		model.addAttribute("courseId", courseId);
		model.addAttribute("studentId", studentId);
		
		return "update-grades";
	}
	
	@PostMapping("/student-grades/save")
	public String saveStudentGrades(
			@ModelAttribute("studentRegistrationForm") StudentRegistration studentRegistration,
			@RequestParam("courseId") Long courseId,
			@RequestParam("studentId") Long studentId,
			Model model) {	
		// Manual update is required for studentRegistration fields
		// because studentRegistration has a composite PK
		Course course = courseService.findById(courseId);
		Student student = studentService.findById(studentId);
		studentRegistration.updateStudentRegistration(student, course);

		// Save modified student registration on database
		studentRegistrationService.save(studentRegistration);
		
		return "redirect:/student-grades?courseId=" + courseId;
	}
}
