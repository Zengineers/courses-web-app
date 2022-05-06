package com.zengineers.courses.service;

import static com.zengineers.courses.MockObjectGenerator.createMockCourse;
import static com.zengineers.courses.MockObjectGenerator.createMockStudent;
import static com.zengineers.courses.MockObjectGenerator.createMockStudentRegistration;
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

import com.zengineers.courses.model.Course;
import com.zengineers.courses.model.Student;
import com.zengineers.courses.model.StudentRegistration;
import com.zengineers.courses.model.id.StudentRegistrationId;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class StudentRegistrationServiceTest {

	@Autowired
	private StudentRegistrationService studentRegistrationService;
	
	@Test
	void testStudentRegistrationServiceIsNotNull() {
		Assertions.assertNotNull(studentRegistrationService);
	}
	
	@ParameterizedTest
	@MethodSource("generateMockStudentRegistrations")
	void testFindById(StudentRegistration expectedStudentReg) {
		StudentRegistrationId expectedStudentRegId = expectedStudentReg.getStudentRegistrationId();
		Student expectedStudent = expectedStudentReg.getStudent();
		Course expectedCourse = expectedStudentReg.getCourse();
		StudentRegistration studentReg = studentRegistrationService.findById(expectedStudentRegId);
		
		Assertions.assertNotNull(studentReg);
		Assertions.assertNotNull(studentReg.getStudentRegistrationId());
		Assertions.assertNotNull(studentReg.getStudent());
		Assertions.assertNotNull(studentReg.getCourse());
		Assertions.assertNotNull(studentReg.getCourse().getInstructor());
		
		assertThat(studentReg.getStudentRegistrationId().equals(expectedStudentRegId));		
		assertThat(studentReg.getStudent().equals(expectedStudent));
		assertThat(studentReg.getCourse().equals(expectedCourse));
		
		assertThat(studentReg.getStudent().getId()).isEqualTo(expectedStudent.getId());
		assertThat(studentReg.getStudent().getRegistrationNumber()).isEqualTo(expectedStudent.getRegistrationNumber());
		assertThat(studentReg.getStudent().getFullName()).isEqualTo(expectedStudent.getFullName());
		assertThat(studentReg.getStudent().getSemester()).isEqualTo(expectedStudent.getSemester());
		assertThat(studentReg.getStudent().getRegistrationYear()).isEqualTo(expectedStudent.getRegistrationYear());
		assertThat(studentReg.getStudent().getMail()).isEqualTo(expectedStudent.getMail());
		assertThat(studentReg.getStudent().getDepartment()).isEqualTo(expectedStudent.getDepartment());
		
		assertThat(studentReg.getCourse().getId()).isEqualTo(expectedCourse.getId());
		assertThat(studentReg.getCourse().getCode()).isEqualTo(expectedCourse.getCode());
		assertThat(studentReg.getCourse().getName()).isEqualTo(expectedCourse.getName());
		assertThat(studentReg.getCourse().getSyllabus()).isEqualTo(expectedCourse.getSyllabus());
		assertThat(studentReg.getCourse().getYear()).isEqualTo(expectedCourse.getYear());
		assertThat(studentReg.getCourse().getSemester()).isEqualTo(expectedCourse.getSemester());
		
		// Must create mock instructor to assert instructor id
		// assertNotNull for instructor field of course should be enough for this test
		// assertThat(studentReg.getCourse().getInstructor().getId()).isEqualTo(expectedCourse.getInstructor().getId());
	}
	
	@ParameterizedTest
	@CsvSource(value = {"1","2","3","4"})
	void testExistsByStudentId(Long studentId) {
		boolean exists = studentRegistrationService.existsByStudentId(studentId);
		Assertions.assertTrue(exists);
	}
	
	@ParameterizedTest
	@CsvSource(value = {"15","200000"})
	void testExistsByStudentIdFailure(Long studentId) {
		boolean exists = studentRegistrationService.existsByStudentId(studentId);
		Assertions.assertFalse(exists);
	}
	
	@ParameterizedTest
	@MethodSource("generateMockStudentRegistrations")
	@Transactional
	void testSave(StudentRegistration studentRegistration) {
		try {
			studentRegistrationService.save(studentRegistration);
		} catch (Exception e) {
			fail("Should not have thrown any exception (testSave).");
		}
	}
	
	@ParameterizedTest
	@MethodSource("generateMockStudentRegistrations")
	@Transactional
	void testDelete(StudentRegistration studentRegistration) {
		StudentRegistrationId studentRegId = studentRegistration.getStudentRegistrationId();
		try {
			studentRegistrationService.delete(studentRegId);
		} catch (Exception e) {
			fail("Should not have thrown any exception (testDelete).");
		}	
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
