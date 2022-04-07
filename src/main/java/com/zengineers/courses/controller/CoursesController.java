package com.zengineers.courses.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengineers.courses.config.InstructorAuthentication;
import com.zengineers.courses.model.Course;
import com.zengineers.courses.model.Instructor;
import com.zengineers.courses.service.CourseService;
import com.zengineers.courses.service.StudentRegistrationService;

@Controller
@RequestMapping("/")
public class CoursesController {
	
	private CourseService courseService;
	private StudentRegistrationService studentRegistrationService;

	public CoursesController(CourseService courseService) {
		this.courseService = courseService;
	} 
	
	private Instructor getAuthenticatedInstructor() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		InstructorAuthentication auth = (InstructorAuthentication) securityContext.getAuthentication().getPrincipal();
		return auth.getInstructor();
	}

	@GetMapping({"/courses", "/"})
	public String listCourses(Model model) {		
		List<Course> courses = courseService.findCoursesByInstructorId(getAuthenticatedInstructor().getId());
		model.addAttribute("courses", courses);
		return "courses";
	}

	@GetMapping("/courses/add")
	public String addCourse(Model model) {
		model.addAttribute("courseForm", new Course());
		return "add-update-course";
	}
	
	@PostMapping("/courses/save")
	public String saveCourse(@ModelAttribute("courseForm") Course course) {
		course.setInstructor(getAuthenticatedInstructor());
		courseService.save(course);
		return "redirect:/courses";
	}
	
	@GetMapping("/courses/delete")
	public String delete(@RequestParam("courseId") Long courseId) {
		courseService.delete(courseId);
		return "redirect:/courses";
	}
	
	@GetMapping("/courses/update")
	public String updateCourse(@RequestParam("courseId") Long courseId, Model model) {
		Course course = courseService.update(courseId);
		model.addAttribute("courseForm", course);
		return "add-update-course";
	}
	
}
