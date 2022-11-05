package com.gl.emp.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gl.emp.model.Employee;
import com.gl.emp.model.Role;
import com.gl.emp.model.User;
import com.gl.emp.repository.EmployeeRepository;
import com.gl.emp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootstrapAppData {
	
	private final EmployeeRepository employeeRepository;
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void insertEmployees(ApplicationReadyEvent event) {
		User santhosh = new User();
		santhosh.setUsername("santhosh");
		santhosh.setPassword(this.passwordEncoder.encode("welcome"));
		santhosh.setEmailAddress("santhosh@gmail.com");

		Role raviRole = new Role();
		raviRole.setRoleName("USER");
		
		Role santhoshRole = new Role();
		santhoshRole.setRoleName("ADMIN");
		
		santhoshRole.setUser(santhosh);
		santhosh.addRole(santhoshRole);
		

		User ravi = new User();
		ravi.setUsername("ravi");
		ravi.setPassword(this.passwordEncoder.encode("welcome"));
		ravi.setEmailAddress("ravi@gmail.com");
		ravi.addRole(raviRole);
		this.userRepository.save(ravi);
		this.userRepository.save(santhosh);
	
		
		
	}
}


