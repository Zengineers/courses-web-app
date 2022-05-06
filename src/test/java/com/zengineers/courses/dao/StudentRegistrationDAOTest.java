package com.zengineers.courses.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class StudentRegistrationDAOTest {
	
	@Autowired
	private StudentRegistrationDAO studentRegistrationDAO;
	
	@Test
	void testStudentRegistrationDAOIsNotNull() {
		Assertions.assertNotNull(studentRegistrationDAO);
	}
	
	@ParameterizedTest
	@CsvSource(value = {"1","2","3","4"})
	void testExistsByStudentId(Long studentId) {
		boolean exists = studentRegistrationDAO.existsByStudentId(studentId);
		Assertions.assertTrue(exists);
	}
	
	@ParameterizedTest
	@CsvSource(value = {"15","200000"})
	void testExistsByStudentIdFailure(Long studentId) {
		boolean exists = studentRegistrationDAO.existsByStudentId(studentId);
		Assertions.assertFalse(exists);
	}
	
}
