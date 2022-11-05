package com.gl.emp.controller;

import java.util.List;
import java.util.Set;

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
import com.gl.emp.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeRestController {
	
	private final EmployeeService employeeService;
	
	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		return this.employeeService.saveEmployee(employee);
	}
	
	@GetMapping
	public Set<Employee> fetchAllEmployees() {
		return this.employeeService.fetchAllEmployees();
	}
	
	@GetMapping("/{id}")
	public Employee fetchEmployeeById(@PathVariable("id") long empId) {
		return this.employeeService.fetchEmployeeById(empId);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployeeById(@PathVariable("id") long empId) {
		this.employeeService.deleteEmployeeById(empId);
	}
	
	@PutMapping("/{id}")
    public Employee updateEmployee( @PathVariable("id") long emplloyeeid , @RequestBody Employee employee)
	{
		return this.employeeService.updateemployee(emplloyeeid, employee);
	} 
	
	@GetMapping("/asc")
	public List<Employee> sortemployeesbyasc() {
		return this.employeeService.sortbyfirstnameasc();
	}
		
	@GetMapping("/dsc")
		public List<Employee> sortemployeesbydsc() {
	   return this.employeeService.sortbyfirstnamedsc();
	}

	@GetMapping("firstname/{firstname}")
	public List<Employee> getbyname(@PathVariable String firstname)
	{
		return this.employeeService.findbyfirstname(firstname);
		
	}



}


