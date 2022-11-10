package com.gl.emp.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Sort;

import com.gl.emp.model.Employee;
import com.gl.emp.model.Role;
import com.gl.emp.model.User;
import com.gl.emp.repository.EmployeeRepository;
import com.gl.emp.repository.RoleRepository;
import com.gl.emp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService implements Employeeinterface {

	@Autowired
	private EmployeeRepository employeerepository;

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	// to save user

	public User saveUser(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(user);

	}

	// to save role

	public Role saveRole(Role role) {

		return roleRepository.save(role);
	}

	// TO ADD EMPLOYEE
	public Employee saveEmployee(Employee employee) {
		return this.employeerepository.save(employee);
	}

	// TO FETCH ALL EMPLOYEE
	public Set<Employee> fetchAllEmployees() {
		return new HashSet<>(this.employeerepository.findAll());
	}

	// TO FETCH EMPLOYEE BY ID
	public Employee fetchEmployeeById(long empId) {
		Optional<Employee> optionalemployee = this.employeerepository.findById(empId);
		if (optionalemployee.isPresent()) {
			return optionalemployee.get();
		} else {
			throw new IllegalArgumentException("invalid employee id is passed");
		}

	}

	// TO DELETE EMPLOYEE
	public void deleteEmployeeById(long empId) {
		this.employeerepository.deleteById(empId);
	}

	// TO UPDATE EMPLOYEE
	public Employee updateemployee(long emplloyeeid, Employee employee) {

		Employee repositoryemployee = this.fetchEmployeeById(emplloyeeid);
		repositoryemployee.setEmail(employee.getEmail());
		repositoryemployee.setFirstname(employee.getFirstname());
		repositoryemployee.setLastname(employee.getLastname());
		return this.employeerepository.save(repositoryemployee);
	}

	// TO SORT BY FIRST NAME ASCENDING
	public List<Employee> sortbyfirstnameasc() {

		return this.employeerepository.findAll(Sort.by("firstname").ascending());

	}

	// TO SORT BY FIRSTNAME DESCENDING
	public List<Employee> sortbyfirstnamedsc() {

		return this.employeerepository.findAll(Sort.by("firstname").descending());

	}

	// GET EMPLOYEES BY FIRSTNAME
	public List<Employee> findbyfirstname(String name) {

		return this.employeerepository.findByfirstname(name);
	}

}
