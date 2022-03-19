package com.zengineers.courses.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
	
	
	@ParameterizedTest
	@CsvSource(value = {"1-user123-$2a$07$ma0A4JMxtia8wxOR4ljnxuVUP.2NB4.sz/ceUC0lhYeSkY0AqNO1i-jack@gmail.com",
										"2-user1-$2a$07$aEZe19IR19jYGVrM87/jZu1hq6GQx44kVEJtE5Kz9MfY4cYNasjB6-max1@yahoo.gr-stable"}, 
							delimiter = '-')
	public void testInstructorDAO(String id, String username, String password, String mail) {		
		Instructor instructor = instructorDAO.findByUsername(username);
		
		assertThat(instructor.getId()).isEqualTo(Long.parseLong(id));
		assertThat(instructor.getUsername()).isEqualTo(username);
		assertThat(instructor.getPassword()).isEqualTo(password);
		assertThat(instructor.getMail()).isEqualTo(mail);
	}
}
