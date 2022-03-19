package com.zengineers.courses.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.TestPropertySource;

import com.zengineers.courses.config.InstructorAuthentication;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class InstructorAuthenticationServiceTest {

	@Autowired
	private InstructorAuthenticationService instructorAuthenticationService;
	private InstructorAuthentication instructorAuthentication;

	@Test
	void testInstructorAuthenticationServiceIsNotNull() {
		Assertions.assertNotNull(instructorAuthenticationService);
	}
	
	/* This test expects valid usernames and passwords (existent in the database) */
	@ParameterizedTest
	@CsvSource(value = {"user123-$2a$07$ma0A4JMxtia8wxOR4ljnxuVUP.2NB4.sz/ceUC0lhYeSkY0AqNO1i",
										"user1-$2a$07$aEZe19IR19jYGVrM87/jZu1hq6GQx44kVEJtE5Kz9MfY4cYNasjB6"}, 
							delimiter = '-')
	public void testInstructorAuthenticationServiceSuccess(String username, String password) {
		instructorAuthentication = (InstructorAuthentication) instructorAuthenticationService.loadUserByUsername(username);
		
		assertThat(instructorAuthentication.getUsername()).isEqualTo(username);
		assertThat(instructorAuthentication.getPassword()).isEqualTo(password);
		assertThat(instructorAuthentication.isAccountNonExpired()).isEqualTo(true);
		assertThat(instructorAuthentication.isAccountNonLocked()).isEqualTo(true);
		assertThat(instructorAuthentication.isCredentialsNonExpired()).isEqualTo(true);
		assertThat(instructorAuthentication.isEnabled()).isEqualTo(true);
	}
	
	/* This test expects invalid usernames (non-existent in the database) */
	@ParameterizedTest
	@CsvSource(value = {"user2","user12"})
	public void testInstructorAuthenticationServiceFailure(String username) {
		Exception exception = Assertions.assertThrows(UsernameNotFoundException.class, () -> {
			instructorAuthenticationService.loadUserByUsername(username);
	    });
		
		String expectedMessage = "Username not found";
	    String actualMessage = exception.getMessage();
	    
	    Assertions.assertEquals(expectedMessage, actualMessage);
	    Assertions.assertNull(instructorAuthentication);
	}
	
	

}