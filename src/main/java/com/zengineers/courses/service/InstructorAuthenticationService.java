package com.zengineers.courses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zengineers.courses.config.InstructorAuthentication;
import com.zengineers.courses.dao.InstructorDAO;
import com.zengineers.courses.model.Instructor;

public class InstructorAuthenticationService implements UserDetailsService {

	@Autowired
	private InstructorDAO instructorDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Instructor instructor = instructorDAO.findByUsername(username);
		if (instructor == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return new InstructorAuthentication(instructor);
	}

}
