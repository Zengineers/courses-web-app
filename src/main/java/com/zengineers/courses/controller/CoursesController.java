package com.zengineers.courses.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengineers.courses.model.Course;
import com.zengineers.courses.service.CourseService;
import com.zengineers.courses.service.StudentRegistrationService;

@Controller
//@RequestMapping("/")
public class CoursesController {
	
	private CourseService courseService;
	private StudentRegistrationService studentRegistrationService;

	public CoursesController(CourseService courseService) {
		this.courseService = courseService;
	} 
	
	@GetMapping({"/home", "/"})
	public String listCourses(Model model) {
		List<Course> courses = courseService.findAll();
		model.addAttribute("courses", courses);
		return "home";
	}

	@GetMapping("/addCourse")
	public String addCourse(Model model) {
		model.addAttribute("courseForm", new Course());
		return "add-course";
	}
	
	@PostMapping("/save")
	public String saveCourse(@ModelAttribute("courseForm") Course course) {
		courseService.save(course);
		return "redirect:/home";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("courseId") Long courseId) {
		courseService.delete(courseId);
		return "redirect:/home";
	}
	
	@GetMapping("/update")
	public String updateCourse(@RequestParam("courseId") Long courseId, Model model) {
		Course course = courseService.update(courseId);
		model.addAttribute("courseForm", course);
		return "add-course";
	}
	
}
