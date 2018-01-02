package com.sashi.ems.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.sashi.ems.model.Employee;


public interface EmployeeRepository<P> extends CrudRepository<Employee, Integer> {
	List<Employee> findByCompanyId(int id);
	
	@Query("select avg(e.salary) from Employee e where e.company.id=?1")
    Double findAvgSalaryByCompany(int id);
}
