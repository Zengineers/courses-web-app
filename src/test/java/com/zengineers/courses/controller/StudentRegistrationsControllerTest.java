package com.zengineers.courses.controller;

import static com.zengineers.courses.MockObjectGenerator.createMockCourse;
import static com.zengineers.courses.MockObjectGenerator.createMockStudent;
import static com.zengineers.courses.MockObjectGenerator.createMockStudentRegistration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.zengineers.courses.model.StudentRegistration;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureMockMvc
public class StudentRegistrationsControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private StudentRegistrationsController studentRegistrationController;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testAutowiredFieldsAreNotNull() {
		Assertions.assertNotNull(context);
		Assertions.assertNotNull(mockMvc);
		Assertions.assertNotNull(studentRegistrationController);
	}
	
	@ParameterizedTest
	@CsvSource(value = {"2"})
	@WithUserDetails("user1")
	public void testListStudentRegistrationsReturnsPageForUser1(Long courseId) throws Exception {
		mockMvc.perform(get("/student-registrations?courseId=" + courseId)).
		andExpect(status().isOk()).
		andExpect(view().name("student-registrations"));
	}
	
	@ParameterizedTest
	@CsvSource(value = {"1","3"})
	@WithUserDetails("user123")
	public void testListStudentRegistrationsReturnsPageForUser123(Long courseId) throws Exception {
		mockMvc.perform(get("/student-registrations?courseId=" + courseId)).
		andExpect(status().isOk()).
		andExpect(view().name("student-registrations"));
	}
	
	@Test
	@WithUserDetails("user1")
	public void testAddStudentRegistrationReturnsPage() throws Exception {
		mockMvc.perform(
				get("/student-registrations/add?courseId=2"))
				.andExpect(status().isOk())
				.andExpect(view().name("add-update-student-registration"));
	}
	
	@ParameterizedTest
	@CsvSource(value = {"2-1","2-2", "2-3", "2-4"}, delimiter='-')
	@WithUserDetails("user1")
	public void testUpdateStudentRegistrationReturnsPage(String courseId, String studentId) throws Exception {
		mockMvc.perform(
				get("/student-registrations/update?courseId=" + courseId + "&studentId=" + studentId))
				.andExpect(status().isOk())
				.andExpect(view().name("add-update-student-registration"));
	}
	
	@ParameterizedTest
	@WithUserDetails("user1")
	@MethodSource("generateMockStudentRegistrations")
	@Transactional
	public void testSaveStudentRegistrationReturnsPage(StudentRegistration studentReg) throws Exception {
		Long courseId = studentReg.getCourse().getId();
	    mockMvc.perform(
	    		post("/student-registrations/save?courseId=" + courseId)
	    		.flashAttr("studentRegistrationForm", studentReg))
				.andDo(print())
	    		.andExpect(status().isFound())
	    		.andExpect(view().name("redirect:/student-registrations?courseId=" + courseId));
	}
	
	@ParameterizedTest
	@CsvSource(value = {"2-1","2-2", "2-3", "2-4"}, delimiter='-')
	@WithUserDetails("user1")
	@Transactional
	public void testDeleteStudentRegistrationReturnsPage(String courseId, String studentId) throws Exception {
	    mockMvc.perform(
	    		get("/student-registrations/delete?courseId=" + courseId + "&studentId=" + studentId))
	    		.andExpect(status().isFound())
	    		.andExpect(view().name("redirect:/student-registrations?courseId=" + courseId));
	}
	
	static Stream<Arguments> generateMockStudentRegistrations() {
		return Stream.of(
				Arguments.of(createMockStudentRegistration(
						createMockStudent(1L, 2641, "Antoniou Chris", 8, 2020, "ant@yahoo.com", "Dept. Computer Science"),
						createMockCourse(2L, "MYY101", "Calculus I", "Functions of one variable (monotonic, inverse, algebraic, logarithmic functions).", 1, "Winter")
						)),
				Arguments.of(createMockStudentRegistration(
						createMockStudent(2L, 2626, "John John", 2, 2022, "jj@gmail.com", "Dept. Computer Science"),
						createMockCourse(1L, "MYY100", "Databases", "Introduction to Database Management Systems.", 4, "Winter")
						)),
				Arguments.of(createMockStudentRegistration(
						createMockStudent(2L, 2626, "John John", 2, 2022, "jj@gmail.com", "Dept. Computer Science"),
						createMockCourse(2L, "MYY101", "Calculus I", "Functions of one variable (monotonic, inverse, algebraic, logarithmic functions).", 1, "Winter")
						)),
				Arguments.of(createMockStudentRegistration(
						createMockStudent(2L, 2626, "John John", 2, 2022, "jj@gmail.com", "Dept. Computer Science"),
						createMockCourse(3L, "MYY102", "Software Engineering", "Best practices for software development.", 4, "Spring")
						)),
				Arguments.of(createMockStudentRegistration(
						createMockStudent(3L, 3354, "Aggeliki Tsiouri", 8, 2020, "agg@gmail.com", "Dept. Computer Science"),
						createMockCourse(1L, "MYY100", "Databases", "Introduction to Database Management Systems.", 4, "Winter")
						)),
				Arguments.of(createMockStudentRegistration(
						createMockStudent(3L, 3354, "Aggeliki Tsiouri", 8, 2020, "agg@gmail.com", "Dept. Computer Science"),
						createMockCourse(2L, "MYY101", "Calculus I", "Functions of one variable (monotonic, inverse, algebraic, logarithmic functions).", 1, "Winter")
						)),
				Arguments.of(createMockStudentRegistration(
						createMockStudent(4L, 2323, "Sandy Sand", 2, 2022, "sand@gmail.com", "Dept. Computer Science"),
						createMockCourse(1L, "MYY100", "Databases", "Introduction to Database Management Systems.", 4, "Winter")
						)),
				Arguments.of(createMockStudentRegistration(
						createMockStudent(4L, 2323, "Sandy Sand", 2, 2022, "sand@gmail.com", "Dept. Computer Science"),
						createMockCourse(2L, "MYY101", "Calculus I", "Functions of one variable (monotonic, inverse, algebraic, logarithmic functions).", 1, "Winter")
						)),
				Arguments.of(createMockStudentRegistration(
						createMockStudent(4L, 2323, "Sandy Sand", 2, 2022, "sand@gmail.com", "Dept. Computer Science"),
						createMockCourse(3L, "MYY102", "Software Engineering", "Best practices for software development.", 4, "Spring")
						))
				);
	}
}
