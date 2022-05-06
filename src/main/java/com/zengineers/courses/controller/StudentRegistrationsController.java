package com.zengineers.courses.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengineers.courses.model.Course;
import com.zengineers.courses.model.Student;
import com.zengineers.courses.model.StudentRegistration;
import com.zengineers.courses.model.id.StudentRegistrationId;
import com.zengineers.courses.service.CourseService;
import com.zengineers.courses.service.StudentRegistrationService;
import com.zengineers.courses.service.StudentService;

@Controller
public class StudentRegistrationsController {

	private StudentRegistrationService studentRegistrationService;
	private CourseService courseService;
	private StudentService studentService;

	public StudentRegistrationsController(
			StudentRegistrationService studentRegistrationService, 
			CourseService courseService,
			StudentService studentService) {
		super();
		this.studentRegistrationService = studentRegistrationService;
		this.courseService = courseService;
		this.studentService = studentService;
	}
	
	@GetMapping("/student-registrations")
	public String listStudentRegistrations(@RequestParam("courseId") Long courseId, Model model) {
		// Add course to model (to display course code on header)
		Course course = courseService.findById(courseId);
		model.addAttribute("course", course);
		
		// Add student registrations to model
		List<StudentRegistration> studentRegistrations = course.getStudentRegistrations();
		model.addAttribute("studentRegistrations", studentRegistrations);

		return "student-registrations";
	}
	
	@GetMapping("/student-registrations/add")
	public String addStudentRegistration(@RequestParam("courseId") Long courseId, Model model) {
		model.addAttribute("courseId", courseId);
		model.addAttribute("studentRegistrationForm", new StudentRegistration());
		return "add-update-student-registration";
	}
	
	@GetMapping("/student-registrations/update")
	public String updateStudentRegistration(
			@RequestParam("courseId") Long courseId, 
			@RequestParam("studentId") Long studentId,
			Model model) {	
		
		// Find the student registration that was selected for update and add it to model
		StudentRegistrationId studentRegId = new StudentRegistrationId(studentId, courseId);
		StudentRegistration studentReg = studentRegistrationService.findById(studentRegId);
		model.addAttribute("studentRegistrationForm", studentReg);
		
		// Add course id to model since we still need it as a param
		model.addAttribute("courseId", courseId);
		
		return "add-update-student-registration";
	}
	
	@PostMapping("/student-registrations/save")
	public String saveStudentRegistration(
			@ModelAttribute("studentRegistrationForm") StudentRegistration studentRegistration,
			@RequestParam("courseId") Long courseId,
			Model model) {	
		try {
			// Manual update is required for studentRegistration fields
			// because studentRegistration has a composite PK
			Student student = studentService.searchForExistingStudent(studentRegistration.getStudent());
			Course course = courseService.findById(courseId);
			studentRegistration.updateStudentRegistration(student, course);

			// Save modified student and student registration on database
			studentService.save(student);
			studentRegistrationService.save(studentRegistration);
			
			return "redirect:/student-registrations?courseId=" + courseId;
		} 
		catch (Exception e) {
			studentRegistration.setViolatesConstraintsInDatabase(true);
			model.addAttribute("courseId", courseId);
			return "add-update-student-registration";
		}
	}
	
	@GetMapping("/student-registrations/delete")
	public String delete(
			@RequestParam("courseId") Long courseId, 
			@RequestParam("studentId") Long studentId) {
		
		// Delete student registration
		StudentRegistrationId studentRegId = new StudentRegistrationId(studentId, courseId);
		studentRegistrationService.delete(studentRegId);
		
		// Delete student too if there are no registrations for any other courses
		boolean isRegisteredInOtherCourse = studentRegistrationService.existsByStudentId(studentId);
		if (!isRegisteredInOtherCourse) studentService.delete(studentId);
		
		return "redirect:/student-registrations?courseId=" + courseId;
	}
	
}
