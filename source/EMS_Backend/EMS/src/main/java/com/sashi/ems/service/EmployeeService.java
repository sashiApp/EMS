package com.sashi.ems.service;

import java.util.List;

import com.sashi.ems.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	
	public List<Employee> getAllEmployeesByCompany(int id);
	
	public Double getAvgSalaryByCompany(int id);

	
	public Employee getEmployee(int id);
	
	
	public void addEmployee(Employee employee);
	
	public boolean deleteEmployee(int id);
	
	public boolean updateEmployee(Employee employee);
}
