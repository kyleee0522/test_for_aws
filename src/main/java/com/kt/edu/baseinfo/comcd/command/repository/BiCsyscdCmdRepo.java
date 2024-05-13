package com.kt.edu.baseinfo.comcd.command.repository;

import com.kt.edu.baseinfo.comcd.command.repository.entity.BiCsyscdCmdEntt;
import com.kt.edu.baseinfo.comcd.command.repository.sql.BiCsyscdCmdSql;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiCsyscdCmdRepo extends CrudRepository<BiCsyscdCmdEntt, String> {
	
	@Modifying
	@Query(BiCsyscdCmdSql.INSERT_CSYSCD)
	public int insertCsyscd(String grpId, String cd, String name, String val1, String val2, String val3, String val4, String val5);
}
