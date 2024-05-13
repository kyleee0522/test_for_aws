package com.kt.edu.baseinfo.comcd.query.api;

import java.util.List;

import com.kt.edu.baseinfo.comcd.query.controller.BiCsyscdQryCntr;
import com.kt.edu.baseinfo.comcd.query.payload.in.BiCsyscdInQryPyld;
import com.kt.edu.baseinfo.comcd.query.payload.out.BiCsyscdOutListQryPyld;
import com.kt.edu.baseinfo.comcd.query.payload.out.BiCsyscdOutQryPyld;
import com.kt.edu.baseinfo.comcd.query.payload.out.dto.BiCsyscdOutQryDto;
import com.kt.edu.baseinfo.comcd.query.repository.dto.BiCsyscdQryDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/comcd")
@Tag(name = "baseinfo - 기준정보 조회", description = "baseinfo - BI_CSYSCD 테이블 조회")
public class BiCsyscdQryApi {
	private final BiCsyscdQryCntr csyscdQryCntr;
	
	@Operation(summary = "baseinfo - 검색 조건으로 조회", description = "baseinfo - Mybatis 사용")
	@PostMapping(path = "/selectcsyscd")
	public BiCsyscdOutListQryPyld selectCsyscd(@RequestBody BiCsyscdInQryPyld inPyld) {
		log.info("[Mybatis]요청 = "+inPyld.toString());
		List<BiCsyscdQryDto> outList = csyscdQryCntr.selectCsyscd(inPyld.getInDs());
		return BiCsyscdOutListQryPyld.builder().outListDs(outList).build();
	}
	
	@Operation(summary = "baseinfo - ID로 조회", description = "baseinfo - Repository 사용")
	@PostMapping(path = "/findcsyscd")
	public BiCsyscdOutQryPyld findCsyscd(@RequestBody BiCsyscdInQryPyld inPyld) {
		log.info("[Repository]요청 = "+inPyld.toString());
		BiCsyscdOutQryDto outDs = csyscdQryCntr.findCsyscd(inPyld.getInDs());
		return BiCsyscdOutQryPyld.builder().outDs(outDs).build();
	}
}
