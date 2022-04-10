package com.zengineers.courses.controller;

import static com.zengineers.courses.MockObjectGenerator.createMockCourseForm;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureMockMvc
public class CoursesControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private CoursesController coursesController;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testAutowiredFieldsAreNotNull() {
		Assertions.assertNotNull(context);
		Assertions.assertNotNull(mockMvc);
		Assertions.assertNotNull(coursesController);
	}
	
	@Test
	@WithUserDetails("user1")
	public void testListCoursesReturnsPage() throws Exception {
		mockMvc.perform(get("/courses")).
		andExpect(status().isOk()).
		andExpect(view().name("courses"));
	}
	
	@Test
	@WithUserDetails("user1")
	public void testAddCourseReturnsPage() throws Exception {
		mockMvc.perform(
				get("/courses/add"))
				.andExpect(status().isOk())
				.andExpect(view().name("add-update-course"));
	}
	
	@Test
	@WithUserDetails("user1")
	public void testUpdateCourseReturnsPage() throws Exception {
		mockMvc.perform(
				get("/courses/update")
				.param("courseId", "1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("add-update-course"));
	}
	
	@Test
	@WithUserDetails("user1")
	@Transactional
	public void testSaveCourseReturnsPage() throws Exception {    
	    mockMvc.perform(
	    		post("/courses/save")
	    		.params(createMockCourseForm("100")))
	    		.andExpect(status().isFound())
	    		.andExpect(view().name("redirect:/courses"));
	}
	
	@Test
	@WithUserDetails("user1")
	@Transactional
	public void testDeleteCourseReturnsPage() throws Exception {
	    mockMvc.perform(
	    		get("/courses/delete")
	    		.param("courseId", "1"))
	    		.andExpect(status().isFound())
	    		.andExpect(view().name("redirect:/courses"));
	}
	
}


