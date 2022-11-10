package com.gl.emp.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.emp.model.Employee;
import com.gl.emp.model.Role;
import com.gl.emp.model.User;
import com.gl.emp.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeRestController {
	
	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	// to save role
	
		@PostMapping("/role")
		public Role saveRole(@RequestBody Role role) {
			return employeeService.saveRole(role);
		}

	
	// to save  user
	
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return employeeService.saveUser(user);
	}

	
	
	// to fetch all employees
	
	@GetMapping 
	public Set<Employee> fetchAllEmployees() {
		return this.employeeService.fetchAllEmployees();
	}
	
	// To save  employee
	
	@PostMapping ("/employees")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return this.employeeService.saveEmployee(employee);
	}
	

	// to fetch employee by id
	
	@GetMapping("employees/{id}")
	public Employee fetchEmployeeById(@PathVariable("id") long empId) {
		return this.employeeService.fetchEmployeeById(empId);
	}
	
	// to delete employee by id 
	
	@DeleteMapping("employees/{id}")
	public void deleteEmployeeById(@PathVariable("id") long empId) {
		this.employeeService.deleteEmployeeById(empId);
	}
	
	// to update a employee
	
	@PutMapping("employees/{id}")
    public Employee updateEmployee( @PathVariable("id") long emplloyeeid , @RequestBody Employee employee)
	{
		return this.employeeService.updateemployee(emplloyeeid, employee);
	} 
	
	// to sort by ascending
	
	@GetMapping("/sort/asc")
	public List<Employee> sortemployeesbyasc() {
		return this.employeeService.sortbyfirstnameasc();
	}
		
	// to sort by descending

	@GetMapping("/sort/dsc")
		public List<Employee> sortemployeesbydsc() {
	   return this.employeeService.sortbyfirstnamedsc();
	}

	// to find by firstname
	
	@GetMapping("firstname/{firstname}")
	public List<Employee> getbyname(@PathVariable String firstname)
	{
		return this.employeeService.findbyfirstname(firstname);
		
	}



}


