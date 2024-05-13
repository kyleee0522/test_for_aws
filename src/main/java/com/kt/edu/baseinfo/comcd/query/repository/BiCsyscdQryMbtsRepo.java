package com.kt.edu.baseinfo.comcd.query.repository;

import java.util.List;

import com.kt.edu.baseinfo.comcd.query.repository.dto.BiCsyscdQryDto;
import com.kt.edu.baseinfo.comcd.query.repository.sql.BiCsyscdQrySql;
import com.kt.edu.baseinfo.comcd.query.payload.in.dto.BiCsyscdInQryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface BiCsyscdQryMbtsRepo {
	@SelectProvider(type = BiCsyscdQrySql.class, method = "selectCsyscd")
	public List<BiCsyscdQryDto> selectCsyscd(BiCsyscdInQryDto inDto);
}
