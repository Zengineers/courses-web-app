package com.zengineers.courses.dao;

import static com.zengineers.courses.MockObjectGenerator.createMockCourse;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.zengineers.courses.model.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CourseDAOTest {

	@Autowired
	private CourseDAO courseDAO;

	@Test
	void testCourseDAOIsNotNull() {
		Assertions.assertNotNull(courseDAO);
	}
	
	@ParameterizedTest
	@MethodSource("generateTestInputData")
	void testCourseDAO(Long instructorId, List<Course> expectedCourses) {
		List<Course> courses = courseDAO.findCoursesByInstructorId(instructorId);

		Assertions.assertNotNull(courses);
		Assertions.assertEquals(courses.containsAll(expectedCourses), expectedCourses.containsAll(courses));
		Assertions.assertEquals(expectedCourses.size(), courses.size());
		
		for (Course course : courses) {
			assertThat(course.getInstructor().getId()).isEqualTo(instructorId);
		}
	}

	static Stream<Arguments> generateTestInputData() {
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
