package com.zengineers.courses.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.zengineers.courses.model.Instructor;

public class InstructorAuthentication implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Instructor instructor;
	
	public InstructorAuthentication(Instructor instructor) {
		this.instructor = instructor;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return instructor.getPassword();
	}

	@Override
	public String getUsername() {
		return instructor.getUsername();
	}

	public Instructor getInstructor() {
		return instructor;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
