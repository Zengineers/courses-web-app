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
public class StudentGradesControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private StudentGradesController studentGradesController;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testAutowiredFieldsAreNotNull() {
		Assertions.assertNotNull(context);
		Assertions.assertNotNull(mockMvc);
		Assertions.assertNotNull(studentGradesController);
	}
	
	@ParameterizedTest
	@CsvSource(value = {"2"})
	@WithUserDetails("user1")
	public void testListStudentGradesReturnsPageForUser1(Long courseId) throws Exception {
		mockMvc.perform(get("/student-grades?courseId=" + courseId)).
		andExpect(status().isOk()).
		andExpect(view().name("student-grades"));
	}
	
	@ParameterizedTest
	@CsvSource(value = {"1", "3"})
	@WithUserDetails("user123")
	public void testListStudentGradesReturnsPageForUser123(Long courseId) throws Exception {
		mockMvc.perform(get("/student-grades?courseId=" + courseId)).
		andExpect(status().isOk()).
		andExpect(view().name("student-grades"));
	}
	
	@ParameterizedTest
	@CsvSource(value = {"2"})
	@WithUserDetails("user1")
	public void testComputeTotalGradesReturnsPage(Long courseId) throws Exception {
		mockMvc.perform(get("/student-grades/get-total-grades?courseId=" + courseId)).
		andExpect(status().isFound()).
		andExpect(view().name("redirect:/student-grades?courseId=" + courseId));
	}
	
	@ParameterizedTest
	@CsvSource(value = {"2-1","2-2", "2-3", "2-4"}, delimiter='-')
	@WithUserDetails("user1")
	public void testUpdateStudentGradesReturnsPage(String courseId, String studentId) throws Exception {
		mockMvc.perform(
				get("/student-grades/update?courseId=" + courseId + "&studentId=" + studentId))
				.andExpect(status().isOk())
				.andExpect(view().name("update-grades"));
	}
	
	@ParameterizedTest
	@WithUserDetails("user1")
	@MethodSource("generateMockStudentRegistrationsForCourse2")
	@Transactional
	public void testSaveStudentGradesReturnsPage(StudentRegistration studentReg) throws Exception {
		Long courseId = studentReg.getCourse().getId();
		Long studentId = studentReg.getStudent().getId();
	    mockMvc.perform(
	    		post("/student-grades/save?courseId=" + courseId + "&studentId=" + studentId)
	    		.flashAttr("studentRegistrationForm", studentReg))
				.andDo(print())
	    		.andExpect(status().isFound())
	    		.andExpect(view().name("redirect:/student-grades?courseId=" + courseId));
	}
	
	static Stream<Arguments> generateMockStudentRegistrationsForCourse2() {
		return Stream.of(
				Arguments.of(createMockStudentRegistration(
						createMockStudent(1L, 2641, "Antoniou Chris", 8, 2020, "ant@yahoo.com", "Dept. Computer Science"),
						createMockCourse(2L, "MYY101", "Calculus I", "Functions of one variable (monotonic, inverse, algebraic, logarithmic functions).", 1, "Winter")
						)),
				Arguments.of(createMockStudentRegistration(
						createMockStudent(2L, 2626, "John John", 2, 2022, "jj@gmail.com", "Dept. Computer Science"),
						createMockCourse(2L, "MYY101", "Calculus I", "Functions of one variable (monotonic, inverse, algebraic, logarithmic functions).", 1, "Winter")
						)),
				Arguments.of(createMockStudentRegistration(
						createMockStudent(3L, 3354, "Aggeliki Tsiouri", 8, 2020, "agg@gmail.com", "Dept. Computer Science"),
						createMockCourse(2L, "MYY101", "Calculus I", "Functions of one variable (monotonic, inverse, algebraic, logarithmic functions).", 1, "Winter")
						)),
				Arguments.of(createMockStudentRegistration(
						createMockStudent(4L, 2323, "Sandy Sand", 2, 2022, "sand@gmail.com", "Dept. Computer Science"),
						createMockCourse(2L, "MYY101", "Calculus I", "Functions of one variable (monotonic, inverse, algebraic, logarithmic functions).", 1, "Winter")
						))
				);
	}
}
