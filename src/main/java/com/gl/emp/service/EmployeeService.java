package com.gl.emp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.gl.emp.model.Employee;
import com.gl.emp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepository employeerepository;
	
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
		return this.employeerepository.findById(empId).orElseThrow();
	}
	
	// TO DELETE EMPLOYEE
	public void deleteEmployeeById(long empId) {
		this.employeerepository.deleteById(empId);
	}
	
	// TO UPDATE EMPLOYEE
	public Employee updateemployee(long emplloyeeid, Employee employee) 
	{
		
		Employee repositoryemployee =	this.fetchEmployeeById(emplloyeeid);
		repositoryemployee.setEmail(employee.getEmail());
		repositoryemployee.setFirstname(employee.getFirstname());
		repositoryemployee.setLastname(employee.getLastname());
		return this.employeerepository.save(repositoryemployee);
	}
	
	
	// TO SORT BY FIRST NAME ASCENDING
	public List<Employee> sortbyfirstnameasc()
	{
		
		return this.employeerepository.findAll(Sort.by("firstname").ascending());
		
	}
	
	// TO SORT BY FIRSTNAME DESCENDING
	public List<Employee> sortbyfirstnamedsc()
	{
	
		return this.employeerepository.findAll(Sort.by("firstname").descending());
		
	}
    
	// GET EMPLOYEES BY FIRSTNAME
	public List<Employee> findbyfirstname(String name) {
		
		return this.employeerepository.findByfirstname(name);
	}


}

