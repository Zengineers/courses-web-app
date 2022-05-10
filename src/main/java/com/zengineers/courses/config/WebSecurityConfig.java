package com.zengineers.courses.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.zengineers.courses.service.InstructorAuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new InstructorAuthenticationService();
	}
	
	@Bean 
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    }
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
    	// These pages require authentication to access
    	String[] antPatterns = {
    			"/courses", "/", 
    			"/courses/add", 
    			"/courses/save", 
    			"/courses/delete", 
    			"/courses/update",
    			"/student-registrations", 
    			"/student-registrations/add", 
    			"/student-registrations/update", 
    			"/student-registrations/save",
    			"/student-registrations/delete",
    			"/student-grades", 
    			"/student-grades/get-total-grades", 
    			"/student-grades/update", 
    			"/student-grades/save"
    			};
    	
    	http.authorizeRequests()
    		.antMatchers(antPatterns).authenticated()  
    		.anyRequest().permitAll()
    		.and()
    		.formLogin()
    			// Set custom login page
    			.loginPage("/login") 
    			.usernameParameter("username")
    			// Successful login redirects to the below page
    			.defaultSuccessUrl("/courses")
    			.permitAll();
    }
    
}
