package com.zengineers.courses.dao;

import static com.zengineers.courses.MockObjectGenerator.createMockStudent;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.zengineers.courses.model.Student;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class StudentDAOTest {

	@Autowired
	private StudentDAO studentDAO;
	
	@Test
	void testStudentDAOIsNotNull() {
		Assertions.assertNotNull(studentDAO);
	}
	
	@ParameterizedTest
	@MethodSource("generateFindByRegistrationNumberTestData")
	void testFindByRegistrationNumber(Student expectedStudent) {
		int regNumber = expectedStudent.getRegistrationNumber();
		Student student = studentDAO.findByRegistrationNumber(regNumber);
		
		Assertions.assertNotNull(student);
		assertThat(student.equals(expectedStudent));
		assertThat(student.getId()).isEqualTo(expectedStudent.getId());
		assertThat(student.getRegistrationNumber()).isEqualTo(expectedStudent.getRegistrationNumber());
		assertThat(student.getFullName()).isEqualTo(expectedStudent.getFullName());
		assertThat(student.getSemester()).isEqualTo(expectedStudent.getSemester());
		assertThat(student.getRegistrationYear()).isEqualTo(expectedStudent.getRegistrationYear());
		assertThat(student.getMail()).isEqualTo(expectedStudent.getMail());
		assertThat(student.getDepartment()).isEqualTo(expectedStudent.getDepartment());
	}
	
	@ParameterizedTest
	@CsvSource(value = {"15","200000"})
	void testFindByRegistrationNumberFailure(int regNumber) {
		Student student = studentDAO.findByRegistrationNumber(regNumber);
		Assertions.assertNull(student);
	}
	
	@ParameterizedTest
	@CsvSource(value = {"2641","2626","3354","2323"})
	void testExistsByRegistrationNumber(int regNumber) {
		boolean exists = studentDAO.existsByRegistrationNumber(regNumber);
		Assertions.assertTrue(exists);
	}
	
	@ParameterizedTest
	@CsvSource(value = {"15","200000"})
	void testExistsByRegistrationNumberFailure(int regNumber) {
		boolean exists = studentDAO.existsByRegistrationNumber(regNumber);
		Assertions.assertFalse(exists);
	}
	
	static Stream<Arguments> generateFindByRegistrationNumberTestData() {
		return Stream.of(
				Arguments.of(createMockStudent(1L, 2641, "Antoniou Chris", 8, 2020, "ant@yahoo.com", "Dept. Computer Science")),
				Arguments.of(createMockStudent(2L, 2626, "John John", 2, 2022, "jj@gmail.com", "Dept. Computer Science")),
				Arguments.of(createMockStudent(3L, 3354, "Aggeliki Tsiouri", 8, 2020, "agg@gmail.com", "Dept. Computer Science")),
				Arguments.of(createMockStudent(4L, 2323, "Sandy Sand", 2, 2022, "sand@gmail.com", "Dept. Computer Science"))
				);
	}
}
