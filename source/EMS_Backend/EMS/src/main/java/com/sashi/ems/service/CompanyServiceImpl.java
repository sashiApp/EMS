package com.sashi.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sashi.ems.dao.CompanyRepository;
import com.sashi.ems.model.Company;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	CompanyRepository companyRepository;
	
	public List<Company> getAllCommapnies(){
		return (List<Company>) companyRepository.findAll();
	}
	public Company getCompany(int id){
		return  companyRepository.findOne(id);
	}
	public boolean createCompany(String name) {
		Company company = new Company();
		company.setName(name);
		companyRepository.save(company);
		return true;
	}
	@Override
	public boolean deleteCompany(int id) {
		// TODO Auto-generated method stub
		companyRepository.delete(id);
		return true;
	}
}
