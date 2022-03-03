package com.zengineers.courses.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengineers.courses.model.Course;
import com.zengineers.courses.service.CourseService;
import com.zengineers.courses.service.StudentRegistrationService;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class CoursesController {

	private CourseService courseService;
	private StudentRegistrationService studentRegistrationService;

	public CoursesController() {
		// TODO
	} 

	@GetMapping({"/home", "/"})
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("courseForm", new Course());
		return "home";
	}
	
	@PostMapping({"/home", "/"})
	public String saveEmployee(@ModelAttribute("courseForm") Course course, Model model) {
		model.addAttribute("courseForm", course);
		return "home";
	}
}
