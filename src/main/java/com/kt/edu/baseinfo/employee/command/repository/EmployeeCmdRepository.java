package com.kt.edu.baseinfo.employee.command.repository;

import com.kt.edu.baseinfo.employee.command.repository.sql.QueryEmployeeSqls;
import com.kt.edu.baseinfo.employee.command.domain.EmployeeEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCmdRepository extends CrudRepository<EmployeeEntity, Long> {

    @Query(QueryEmployeeSqls.RETV_NEXT_VAL)
    Long retvNextVal();

    @Query(QueryEmployeeSqls.RETV_NEXT_VAL_H2)
    Long retvNextVal_H2();
    
}
