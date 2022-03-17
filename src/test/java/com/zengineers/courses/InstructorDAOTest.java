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
		assertThat(instructor.getPassword()).isEqualTo("$2a$07$ma0A4JMxtia8wxOR4ljnxuVUP.2NB4.sz/ceUC0lhYeSkY0AqNO1i");
		assertThat(instructor.getMail()).isEqualTo("jack@gmail.com");
		
		instructor = entityManager.find(Instructor.class, (long) 2);
		
		assertThat(instructor.getId()).isEqualTo((long) 2);
		assertThat(instructor.getUsername()).isEqualTo("user1");
		assertThat(instructor.getPassword()).isEqualTo("$2a$07$aEZe19IR19jYGVrM87/jZu1hq6GQx44kVEJtE5Kz9MfY4cYNasjB6");
		assertThat(instructor.getMail()).isEqualTo("max1@yahoo.gr");
	}
}
