package com.kt.edu.baseinfo.comcd.query.repository;

import com.kt.edu.baseinfo.comcd.query.payload.in.dto.BiCsyscdInQryDto;
import com.kt.edu.baseinfo.comcd.query.repository.dto.BiCsyscdQryDto;
import com.kt.edu.baseinfo.comcd.query.repository.entity.BiCsyscdQryEntt;
import com.kt.edu.baseinfo.comcd.query.repository.sql.BiCsyscdQrySql2;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiCsyscdQryRepo extends PagingAndSortingRepository<BiCsyscdQryEntt, String>{

	@Query(BiCsyscdQrySql2.RETV_PPON_CONT_PAGE)
	BiCsyscdQryEntt findByGrpIdAndCd(String grpId, String cd);
	@Query(BiCsyscdQrySql2.RETV_CSYS_CD)
	List<BiCsyscdQryDto> selectCsyscd(BiCsyscdInQryDto inDto);
}
