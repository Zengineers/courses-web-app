package com.zengineers.courses.service;

import static com.zengineers.courses.MockObjectGenerator.createMockCourse;
import static com.zengineers.courses.MockObjectGenerator.createMockInstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.zengineers.courses.model.Course;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {

	@Autowired
	private CourseService courseService;
	
	@Test
	void testCourseServiceIsNotNull() {
		Assertions.assertNotNull(courseService);
	}
	
	@ParameterizedTest
	@MethodSource("generateMockCourses")
	void testFindAll(List<Course> expectedCourses) {
		List<Course> courses = courseService.findAll();
		
		Assertions.assertNotNull(courses);
		Assertions.assertEquals(courses.containsAll(expectedCourses), expectedCourses.containsAll(courses));
		Assertions.assertEquals(courses.size(), expectedCourses.size());
	}
	
	@ParameterizedTest
	@MethodSource("generateMockCoursesByInstructorId")
	void testFindCoursesByInstructorId(Long instructorId, List<Course> expectedCourses) {
		List<Course> courses = courseService.findCoursesByInstructorId(instructorId);

		Assertions.assertNotNull(courses);
		Assertions.assertEquals(courses.containsAll(expectedCourses), expectedCourses.containsAll(courses));
		Assertions.assertEquals(expectedCourses.size(), courses.size());
		
		for (Course course : courses) {
			assertThat(course.getInstructor().getId()).isEqualTo(instructorId);
			Assertions.assertNotNull(course.getStudentRegistrations());
		}
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"1", "2", "3"})
	@Transactional
	void testDelete(String id) {
		try {
			courseService.delete(Long.parseLong(id));
		} catch (Exception e) {
			fail("Should not have thrown any exception (testDelete).");
		}		
	}
	
	@ParameterizedTest
	@CsvSource(value = {"1-MYY100-Databases-Introduction to Database Management Systems.-4-Winter-1",
										"2-MYY101-Calculus I-Functions of one variable (monotonic, inverse, algebraic, logarithmic functions).-1-Winter-2",
										"3-MYY102-Software Engineering-Best practices for software development.-4-Spring-1"}, 
							delimiter = '-')
	@Transactional
	void testSave(String id, String code, String name, String syllabus, String year, String semester, String instructorId) {
		Course course = createMockCourse(
				Long.parseLong(id), 
				code, 
				name, 
				syllabus, 
				Integer.parseInt(year), 
				semester);
		
		course.setInstructor(createMockInstructor(
				100L,
				"MockUser",
				"$2a$07$ma0A4JMxtia8wxOR4ljhja23dIX0lhYeSkY0AqNO1i",
				"mock@gmail.com"
				));
		
		try {
			courseService.save(course);
		} catch (Exception e) {
			fail("Should not have thrown any exception (testSave).");
		}
	}
	
	@ParameterizedTest
	@CsvSource(value = {"1-MYY100-Databases-Introduction to Database Management Systems.-4-Winter-1",
										"2-MYY101-Calculus I-Functions of one variable (monotonic, inverse, algebraic, logarithmic functions).-1-Winter-2",
										"3-MYY102-Software Engineering-Best practices for software development.-4-Spring-1"}, 
							delimiter = '-')
	@Transactional
	void testFindById(String id, String code, String name, String syllabus, String year, String semester, String instructorId) {
		Course updatedCourse = courseService.findById(Long.parseLong(id));
		
		Assertions.assertNotNull(updatedCourse);
		assertThat(updatedCourse.getId()).isEqualTo(Long.parseLong(id));
		assertThat(updatedCourse.getCode()).isEqualTo(code);
		assertThat(updatedCourse.getName()).isEqualTo(name);
		assertThat(updatedCourse.getSyllabus()).isEqualTo(syllabus);
		assertThat(updatedCourse.getYear()).isEqualTo(Integer.parseInt(year));
		assertThat(updatedCourse.getSemester()).isEqualTo(semester);
		assertThat(updatedCourse.getInstructor().getId()).isEqualTo(Long.parseLong(instructorId));
	}
	
	static Stream<Arguments> generateMockCourses() {
		return Stream.of(
				Arguments.of(Arrays.asList(
						createMockCourse(1L, "MYY100", "Databases", "Introduction to Database Management Systems.", 4, "Winter"),
						createMockCourse(2L, "MYY101", "Calculus I", "Functions of one variable (monotonic, inverse, algebraic, logarithmic functions).", 1, "Winter"),
						createMockCourse(3L, "MYY102", "Software Engineering", "Best practices for software development.", 4, "Spring")
						))
				);
	}
	
	static Stream<Arguments> generateMockCoursesByInstructorId() {
		return Stream.of(
				Arguments.of(1L, Arrays.asList(
						createMockCourse(1L, "MYY100", "Databases", "Introduction to Database Management Systems.", 4, "Winter"),
						createMockCourse(3L, "MYY102", "Software Engineering", "Best practices for software development.", 4, "Spring"))
						), 
				Arguments.of(2L, Arrays.asList(
						createMockCourse(2L, "MYY101", "Calculus I", "Functions of one variable (monotonic, inverse, algebraic, logarithmic functions).", 1, "Winter")) 
						)
				);
	}

}
