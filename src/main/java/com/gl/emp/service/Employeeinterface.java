package com.gl.emp.service;

import java.util.List;
import java.util.Set;

import com.gl.emp.model.Employee;
import com.gl.emp.model.Role;
import com.gl.emp.model.User;

public interface Employeeinterface {
	public Employee saveEmployee(Employee employee);

	public Set<Employee> fetchAllEmployees();

	public Employee fetchEmployeeById(long empId);

	public void deleteEmployeeById(long empId);

	public Employee updateemployee(long emplloyeeid, Employee employee);

	public List<Employee> sortbyfirstnameasc();

	public List<Employee> sortbyfirstnamedsc();

	public List<Employee> findbyfirstname(String name);

	public User saveUser(User user);

	public Role saveRole(Role role);

}
