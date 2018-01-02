package com.sashi.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sashi.ems.model.Company;
import com.sashi.ems.model.EmsResponse;
import com.sashi.ems.model.EmsStatus;
import com.sashi.ems.model.Login;
import com.sashi.ems.service.CompanyService;
import com.sashi.ems.model.Constants;
import com.sashi.ems.model.Employee;

/**
 * REST Controller to publish Company CRUD operations.
 * @author sashi
 *
 */
@CrossOrigin
@RestController
public class CompanyController {
	
	@Autowired
	CompanyService  companyService;

	/*
	* Get all the companies present in the system. 
	* @param
	* @return ResponseEntity with List of companies
	*/
	@RequestMapping(value="/company", method=RequestMethod.GET)
	public ResponseEntity<EmsResponse> getAllCompanies(){
		EmsResponse<List<?>> emsResponse = new EmsResponse<List<?>>();
		emsResponse.setData(companyService.getAllCommapnies());
		emsResponse.setEmsStatus(EmsStatus.ok());
		return  new ResponseEntity<EmsResponse>(emsResponse,HttpStatus.OK);

	}
	
	/*
	* Get the company info of given company ID. 
	* @param int company ID
	* @return ResponseEntity with Company info.
	*/
	@RequestMapping(value="/company/{id}", method=RequestMethod.GET)
	public ResponseEntity<EmsResponse<Company>> getCompany(@PathVariable int id){
		EmsResponse<Company> emsResponse = new EmsResponse<Company>();
		
		emsResponse.setData(companyService.getCompany(id));
		emsResponse.setEmsStatus(EmsStatus.ok());
		return  new ResponseEntity<EmsResponse<Company>>(emsResponse, HttpStatus.OK);
			
		
	}
	
	/*
	* Delete the company info of given company ID. 
	* @param int company ID
	* @return ResponseEntity with s.
	*/
	@RequestMapping(value="/company/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<EmsResponse> deleteCompany(@PathVariable int id){
		EmsResponse emsResponse = new EmsResponse<Company>();
		
		emsResponse.setData(companyService.deleteCompany(id));
		emsResponse.setEmsStatus(EmsStatus.ok());
		return  new ResponseEntity<EmsResponse>(emsResponse, HttpStatus.OK);
			
		
	}
	
	/*
	* Create the company. 
	* @param Company 
	* @return ResponseEntity with status.
	*/
	
	@RequestMapping(value="/company", method=RequestMethod.POST)
	public ResponseEntity<EmsResponse> createCompany(@RequestBody Company company){
		EmsResponse emsResponse = new EmsResponse<Company>();
		
		emsResponse.setData(companyService.createCompany(company.getName()));
		emsResponse.setEmsStatus(EmsStatus.ok());
		return new ResponseEntity<EmsResponse>(emsResponse, HttpStatus.OK);
	}
	/*
	* Login with given credentials. 
	* @param Login
	* @return ResponseEntity with access token.
	*/
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public  ResponseEntity<EmsResponse<String>> login(@RequestBody Login login){
		EmsResponse<String> emsResponse = new EmsResponse();
		if(isValid(login)) {
			int companyID = Constants.getCompanyID(login.getUsername());
			
			emsResponse.setData(Constants.COMPANIES.get(companyID-1));
			emsResponse.setEmsStatus(EmsStatus.ok());
			return  new ResponseEntity<EmsResponse<String>>(emsResponse, HttpStatus.OK);
		}
		emsResponse.setEmsStatus(EmsStatus.unauthorized());
		return  new ResponseEntity<EmsResponse<String>>(emsResponse, HttpStatus.UNAUTHORIZED);
	
				
	}
	
	
	/*
	* Check validity of Login credentials. 
	* @param Company 
	* @return boolean valid or not.
	*/
	 boolean isValid(Login login){
		if(login.getUsername()!= null && login.getUsername().startsWith("admin") && login.getPassword() != null && login.getPassword().startsWith("admin")) {
			return true;
		}
		return false;
		
	}
	
}
