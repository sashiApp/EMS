package com.sashi.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sashi.ems.model.Constants;
import com.sashi.ems.model.Employee;
import com.sashi.ems.model.EmsResponse;
import com.sashi.ems.model.EmsStatus;
import com.sashi.ems.service.EmployeeService;




/**
 * REST Controller to publish employee CRUD operations.
 * @author sashi
 *
 */
@CrossOrigin
@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/*
	* Get all the employees of all the existing companies.
	* @param
	* @return ResponseEntity with List of employees
	*/
	@RequestMapping(value="/employee", method=RequestMethod.GET)
	public  ResponseEntity<EmsResponse>  getEmployees() {
		EmsResponse<List<Employee>> emsResponse = new EmsResponse<List<Employee>>();
		emsResponse.setEmsStatus(EmsStatus.ok());
		emsResponse.setData(employeeService.getAllEmployees());
		return new ResponseEntity<EmsResponse>(emsResponse, HttpStatus.OK) ;
	}
	
	/*
	* Get all the employees of of a given company.
	* @param
	* @return ResponseEntity with List of employees
	*/
	@RequestMapping(value="/employeeByCompany/{companyId}", method=RequestMethod.GET)
	public  ResponseEntity<EmsResponse> getEmployeesByCompany(@RequestHeader(value="AccessToken") String accessToken,@PathVariable Integer companyId) {
		
		EmsResponse<List<Employee>> emsResponse = new EmsResponse<List<Employee>>();
		//Review: Should be Spring security or interceptor to check authorization
		if(accessToken == null || !Constants.isValidToken(companyId,accessToken)) {
			emsResponse.setEmsStatus(EmsStatus.unauthorized());
			return new ResponseEntity<EmsResponse>(emsResponse, HttpStatus.UNAUTHORIZED) ;

		}
		emsResponse.setEmsStatus(EmsStatus.ok());
		emsResponse.setData(employeeService.getAllEmployeesByCompany(companyId));
		return new ResponseEntity<EmsResponse>(emsResponse, HttpStatus.OK) ;
	}
	
	/*
	* Get Average salary of a given company employees .
	* @param
	* @return ResponseEntity with Average salary.
	*/
	@RequestMapping(value="/avgSalary/{companyId}", method=RequestMethod.GET)
	public  ResponseEntity<EmsResponse> getAvgSalaryByCompany(@RequestHeader(value="AccessToken") String accessToken,@PathVariable Integer companyId) {
		EmsResponse<Double> emsResponse = new EmsResponse<Double>();
		if(accessToken == null || !Constants.isValidToken(companyId,accessToken)) {
			emsResponse.setEmsStatus(EmsStatus.unauthorized());
			return new ResponseEntity<EmsResponse>(emsResponse, HttpStatus.UNAUTHORIZED) ;

		}
		emsResponse.setEmsStatus(EmsStatus.ok());
		emsResponse.setData(employeeService.getAvgSalaryByCompany(companyId));
		return new ResponseEntity<EmsResponse>(emsResponse, HttpStatus.OK) ;
	}
	
	/*
	* Get an employee with a given ID.
	* @param
	* @return ResponseEntity with employee.
	*/
	@RequestMapping(value="/employee/{id}", method=RequestMethod.GET)
	public  ResponseEntity<EmsResponse<Employee>> getEmployee(@PathVariable int id) {
		EmsResponse<Employee> emsResponse = new EmsResponse<Employee>();
		if(employeeService.getEmployee(id)!= null) {
			emsResponse.setEmsStatus(EmsStatus.ok());
			emsResponse.setData(employeeService.getEmployee(id));
		}else {
			new ResponseEntity<EmsResponse<Employee>>(emsResponse,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<EmsResponse<Employee>>(emsResponse,HttpStatus.OK);
	}
	
	/*
	* Add employee to the given company.
	* @param
	* @return ResponseEntity with employee.
	*/
	@RequestMapping(value ="/employee", method=RequestMethod.POST)
	public  ResponseEntity<EmsResponse> addEmployee(@RequestHeader(value="AccessToken") String accessToken,@RequestBody Employee employee) {
		EmsResponse<Employee> emsResponse = new EmsResponse<>();
		if(accessToken == null || !Constants.isValidToken(employee.getCompany().getId(),accessToken)) {
			emsResponse.setEmsStatus(EmsStatus.unauthorized());
			return new ResponseEntity<EmsResponse>(emsResponse, HttpStatus.UNAUTHORIZED) ;

		}
		employeeService.addEmployee(employee);
		
		emsResponse.setEmsStatus(EmsStatus.ok());
		return new ResponseEntity<EmsResponse>(emsResponse,HttpStatus.OK);
	}
	
	/*
	* Update an employee with a given employee details.
	* @param
	* @return ResponseEntity with the status.
	*/
	@RequestMapping(value="/employee", method=RequestMethod.PUT)
	public  ResponseEntity<EmsResponse<Employee>> updateEmployee(@RequestHeader(value="AccessToken") String accessToken,@RequestBody Employee employee) {
		EmsResponse<Employee> emsResponse = new EmsResponse<Employee>();
		if(accessToken == null || !Constants.isValidToken(employee.getCompany().getId(),accessToken)) {
			emsResponse.setEmsStatus(EmsStatus.unauthorized());
			return new ResponseEntity<EmsResponse<Employee>>(emsResponse, HttpStatus.UNAUTHORIZED) ;

		}
		if(employeeService.updateEmployee(employee)) {
			 emsResponse.setEmsStatus(EmsStatus.ok());
			 return new ResponseEntity<EmsResponse<Employee>>(emsResponse,HttpStatus.OK);
		}else {
			emsResponse.setEmsStatus(EmsStatus.notFound());
			return new ResponseEntity<EmsResponse<Employee>>(emsResponse,HttpStatus.NOT_FOUND);
		}
	}
	
	/*
	* Delete an employee with a given ID.
	* @param
	* @return ResponseEntity with the status.
	*/
	@RequestMapping(value="/employee/{id}", method=RequestMethod.DELETE)
	public  ResponseEntity<EmsResponse<Employee>> deleteEmployee(@RequestHeader(value="AccessToken") String accessToken,@PathVariable int id) {
		EmsResponse<Employee> emsResponse = new EmsResponse<Employee>();
		if(accessToken == null) {
			emsResponse.setEmsStatus(EmsStatus.unauthorized());
			return new ResponseEntity<EmsResponse<Employee>>(emsResponse, HttpStatus.UNAUTHORIZED) ;

		}
		if(employeeService.deleteEmployee(id)) {
			emsResponse.setEmsStatus(EmsStatus.ok());
			return new ResponseEntity<EmsResponse<Employee>>(emsResponse,HttpStatus.OK);
		}else {
			emsResponse.setEmsStatus(EmsStatus.notFound());
			return new ResponseEntity<EmsResponse<Employee>>(emsResponse,HttpStatus.NOT_FOUND);
		}
	}

	
}
