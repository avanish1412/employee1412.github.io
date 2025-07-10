package com.infosys.employee.repository;

import com.infosys.employee.dao.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepository extends MongoRepository<Employee,String> {

}
