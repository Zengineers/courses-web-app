package com.zengineers.courses.service;

import static com.zengineers.courses.MockObjectGenerator.createMockStudent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

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

import com.zengineers.courses.model.Student;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class StudentServiceTest {

	@Autowired
	private StudentService studentService;
	
	@Test
	void testStudentServiceIsNotNull() {
		Assertions.assertNotNull(studentService);
	}
	
	@ParameterizedTest
	@CsvSource(value = {"2641","2626","3354","2323"})
	void testExistsByRegistrationNumber(int regNumber) {
		boolean exists = studentService.existsByRegistrationNumber(regNumber);
		Assertions.assertTrue(exists);
	}
	
	@ParameterizedTest
	@CsvSource(value = {"15","200000"})
	void testExistsByRegistrationNumberFailure(int regNumber) {
		boolean exists = studentService.existsByRegistrationNumber(regNumber);
		Assertions.assertFalse(exists);
	}
	
	@ParameterizedTest
	@MethodSource("generateMockStudents")
	void testFindByRegistrationNumber(Student expectedStudent) {
		int regNumber = expectedStudent.getRegistrationNumber();
		Student student = studentService.findByRegistrationNumber(regNumber);
		
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
		Student student = studentService.findByRegistrationNumber(regNumber);
		Assertions.assertNull(student);
	}
	
	@ParameterizedTest
	@MethodSource("generateMockStudents")
	@Transactional
	void testSave(Student student) {
		try {
			studentService.save(student);
		} catch (Exception e) {
			fail("Should not have thrown any exception (testSave).");
		}
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"1", "2", "3", "4"})
	@Transactional
	void testDelete(Long studentId) {
		try {
			studentService.delete(studentId);
		} catch (Exception e) {
			fail("Should not have thrown any exception (testDelete).");
		}		
	}
	
	@ParameterizedTest
	@MethodSource("generateMockStudents")
	void testSearchForExistingStudentWithCorrectStudentId(Student expectedStudent) {
		Student student = studentService.searchForExistingStudent(expectedStudent);
		
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
	@MethodSource("generateMockStudents")
	void testSearchForExistingStudentWithIncorrectStudentId(Student expectedStudent) {
		// Setting the studentId to an incorrect value to test whether searchForExistingStudent
		// will set it back to the correct value
		Long expectedStudentId = expectedStudent.getId();
		expectedStudent.setId(-1L);
		Student student = studentService.searchForExistingStudent(expectedStudent);

		Assertions.assertNotNull(student);
		assertThat(student.equals(expectedStudent));
		assertThat(student.getId()).isEqualTo(expectedStudentId);
		assertThat(student.getRegistrationNumber()).isEqualTo(expectedStudent.getRegistrationNumber());
		assertThat(student.getFullName()).isEqualTo(expectedStudent.getFullName());
		assertThat(student.getSemester()).isEqualTo(expectedStudent.getSemester());
		assertThat(student.getRegistrationYear()).isEqualTo(expectedStudent.getRegistrationYear());
		assertThat(student.getMail()).isEqualTo(expectedStudent.getMail());
		assertThat(student.getDepartment()).isEqualTo(expectedStudent.getDepartment());
	}
	
	@ParameterizedTest
	@MethodSource("generateMockStudents")
	void testFindById(Student expectedStudent) {
		Student student = studentService.findById(expectedStudent.getId());
		
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
	
	static Stream<Arguments> generateMockStudents() {
		return Stream.of(
				Arguments.of(createMockStudent(1L, 2641, "Antoniou Chris", 8, 2020, "ant@yahoo.com", "Dept. Computer Science")),
				Arguments.of(createMockStudent(2L, 2626, "John John", 2, 2022, "jj@gmail.com", "Dept. Computer Science")),
				Arguments.of(createMockStudent(3L, 3354, "Aggeliki Tsiouri", 8, 2020, "agg@gmail.com", "Dept. Computer Science")),
				Arguments.of(createMockStudent(4L, 2323, "Sandy Sand", 2, 2022, "sand@gmail.com", "Dept. Computer Science"))
				);
	}
	
}
