package com.zengineers.courses.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengineers.courses.model.Course;
import com.zengineers.courses.model.Student;
import com.zengineers.courses.model.StudentRegistration;
import com.zengineers.courses.model.id.StudentRegistrationId;
import com.zengineers.courses.service.CourseService;
import com.zengineers.courses.service.StudentRegistrationService;

@Controller
public class StudentRegistrationsController {

	private StudentRegistrationService studentRegistrationService;
	private CourseService courseService;

	public StudentRegistrationsController(
			StudentRegistrationService studentRegistrationService, 
			CourseService courseService) {
		super();
		this.studentRegistrationService = studentRegistrationService;
		this.courseService = courseService;
	}
	
	@GetMapping("/student-registrations")
	public String listStudentRegistrations(@RequestParam("courseId") Long courseId, Model model) {
		// Add course to model (to display course code on header)
		Course course = courseService.findById(courseId);
		model.addAttribute("course", course);
		
		// Add students to model
		List<Student> students = course.getStudents();
		model.addAttribute("students", students);

		return "student-registrations";
		
//		List<StudentRegistration> studentRegistrations = course.getStudentRegistrations();
//		model.addAttribute("studentRegistrations", studentRegistrations);
//		
//		for (StudentRegistration s : studentRegistrations ) {
//			String msg = s.getStudent().getId().toString() + " " + 
//					s.getStudent().getFullName().toString() + " " +
//					s.getStudent().getSemester() + " " +
//					s.getStudent().getRegistrationYear() + " " +
//					s.getStudent().getMail().toString() + " " +
//					s.getStudent().getDepartment().toString() + "\n";
//			
//			System.out.println(msg);
//		}
	}
	
	@GetMapping("/student-registrations/add")
	public String addStudentRegistration(@RequestParam("courseId") Long courseId, Model model) {
		model.addAttribute("courseId", courseId);
		model.addAttribute("studentRegistrationForm", new Student());
		return "add-update-student-registration";
	}
	
	@GetMapping("/student-registrations/update")
	public String updateStudentRegistration(
			@RequestParam("courseId") Long courseId, 
			@RequestParam("studentId") Long studentId,
			Model model) {
		
		StudentRegistrationId studentRegId = new StudentRegistrationId(studentId, courseId);
//		studentRegId.setCourseId(courseId);
//		studentRegId.setStudentId(studentId);
		
		StudentRegistration studentReg = studentRegistrationService.findById(studentRegId);
		model.addAttribute("courseId", courseId);
		model.addAttribute("studentRegistrationForm", studentReg.getStudent());
		return "add-update-student-registration";
	}
	
}
