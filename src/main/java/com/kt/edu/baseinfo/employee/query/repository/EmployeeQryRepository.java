package com.kt.edu.baseinfo.employee.query.repository;

import com.kt.edu.baseinfo.employee.query.domain.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeQryRepository extends CrudRepository<EmployeeEntity, Long> {

}
