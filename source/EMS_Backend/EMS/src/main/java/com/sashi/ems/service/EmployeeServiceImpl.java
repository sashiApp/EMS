package com.sashi.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sashi.ems.dao.EmployeeRepository;
import com.sashi.ems.model.Employee;

/**
 * Employee Service class to execute CRUD operations on Employee repository.
 * @author sashi
 *
 */

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	EmployeeRepository<Employee> employeeRepository;


	/*
	* Get all employees from the repo.
	* @param none
	* @return List<Employee>
	*/

	public List<Employee> getAllEmployees(){
		return (List<Employee>)employeeRepository.findAll();
	}
	
	/*
	* Get all employees from the repo.
	* @param none
	* @return List<Employee>
	*/
	
	@Override
	public List<Employee> getAllEmployeesByCompany(int id) {
		// TODO Auto-generated method stub
		return (List<Employee>)employeeRepository.findByCompanyId(id);
	}
	/*
	* Get an employees from the repo by given employee ID.
	* @param int id
	* @return Employee .
	*/
	public Employee getEmployee(int id){
		return (Employee)employeeRepository.findOne(id);
	}
	
	
	/*
	* Add an employee into the repo.
	* @param Employee employee
	* @return 
	*/
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	/*
	* Delete an employee by given ID
	* @param int id
	* @return boolean deleted or not.
	*/
	public boolean deleteEmployee(int id) {
		if(employeeRepository.findOne(id) == null) {
			return false;
		}else {
			employeeRepository.delete(id);
		}
		return true;
	}
	
	/*
	* Update an employee with given employee object
	* @param int id
	* @return boolean updated or not.
	*/
	public boolean updateEmployee(Employee employee) {
		if(employeeRepository.findOne(employee.getId()) == null) {
			return false;
		}else {
			employeeRepository.save(employee);
		}
		return true;
	}

	@Override
	public Double getAvgSalaryByCompany(int id) {
		// TODO Auto-generated method stub
		return employeeRepository.findAvgSalaryByCompany(id);
	}


}
