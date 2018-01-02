package com.sashi.ems.service;

import java.util.List;

import com.sashi.ems.model.Company;

public interface CompanyService {
	public List<Company> getAllCommapnies();
	public Company getCompany(int id);
	public boolean deleteCompany(int id);
	public boolean createCompany(String name);
}
