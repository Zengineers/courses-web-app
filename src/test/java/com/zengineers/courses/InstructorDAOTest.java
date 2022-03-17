package com.zengineers.courses;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.zengineers.courses.dao.InstructorDAO;
import com.zengineers.courses.model.Instructor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class InstructorDAOTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private InstructorDAO instructorDAO;
	
	
	@Test
	public void testInstructorDAO() {		
		Instructor instructor = entityManager.find(Instructor.class, (long) 1);
		
		assertThat(instructor.getId()).isEqualTo((long) 1);
		assertThat(instructor.getUsername()).isEqualTo("user123");
		assertThat(instructor.getPassword()).isEqualTo("pass123");
		assertThat(instructor.getMail()).isEqualTo("jack@gmail.com");
	}
}
