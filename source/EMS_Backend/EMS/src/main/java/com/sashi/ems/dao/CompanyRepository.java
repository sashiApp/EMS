package com.sashi.ems.dao;

import org.springframework.data.repository.CrudRepository;

import com.sashi.ems.model.Company;

public interface CompanyRepository extends CrudRepository<Company,Integer> {

}
