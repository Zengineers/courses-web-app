package com.zengineers.courses.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengineers.courses.model.Course;
import com.zengineers.courses.service.CourseService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class CoursesController {
	
	@Autowired
	private CourseService courseService;
//	private StudentRegistrationService studentRegistrationService;

	public CoursesController(CourseService courseService) {
		this.courseService = courseService;
	} 

//	@GetMapping({"/home", "/"})
//	public String hello(@RequestParam(value = "name", defaultValue = "World") String name, Model model) {
//		model.addAttribute("name", name);
//		model.addAttribute("courseForm", new Course());
//		return "home";
//	}
	
	@GetMapping({"/home", "/"})
	public String listCourses(Model model) {
		List<Course> courses = courseService.findAll();
		model.addAttribute("courses", courses);
		return "home";
	}
	
	@PostMapping({"/home", "/"})
	public String saveName(@ModelAttribute("courseForm") Course course, Model model) {
		model.addAttribute("courseForm", course);
		System.out.println("hi saveName");
		return "home";
	}
	
	@PostMapping("/addCourse")
	public String addCourse(Model model) {
		model.addAttribute("courseForm", new Course());
		System.out.println("hi addCourse POST");
		return "add-course";
	}
	
	@PostMapping("/courses-list")
	public String showCoursesList(@ModelAttribute("courseForm") Course course, Model model) {
		model.addAttribute("courseForm", course);
		printCourse(course);
		courseService.save(course);
		return "courses-list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("courseId") int courseId) {
		courseService.delete(courseId);
		return "redirect:/home";
	}
	
	private void printCourse(Course course) {
		String line = "\n------------------------------------\n";
		String msg = line + "Course name: " + course.getName() + "\n" +
				"Semester: " + course.getSemester() + "\n" +
				"Syllabus: " + course.getSyllabus() + "\n" +
				"Year: " + String.valueOf(course.getYear()) + line + "\n";
//				"Instructor name: " + course.getInstructorName() + line;
		System.out.println(msg);
	}
	
//	@GetMapping("/addCourse")
//	public String addCourse(@ModelAttribute("courseForm") Course course, Model model) {
//		model.addAttribute("courseForm", course);
//		System.out.println("hi addCourse GET");
//		return "add-course";
//	}
}
