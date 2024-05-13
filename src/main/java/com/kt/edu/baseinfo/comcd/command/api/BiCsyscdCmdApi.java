package com.kt.edu.baseinfo.comcd.command.api;

import com.kt.edu.baseinfo.comcd.command.controller.BiCsyscdCmdCntr;
import com.kt.edu.baseinfo.comcd.command.payload.in.BiCsyscdInCmdPyld;
import com.kt.edu.baseinfo.comcd.command.payload.out.BiCsyscdOutCmdPyld;
import com.kt.edu.baseinfo.comcd.command.payload.out.dto.BiCsyscdOutCmdDto;
import com.kt.edu.baseinfo.comcd.command.repository.entity.BiCsyscdCmdEntt;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/api/v1/comcd")
@Tag(name = "baseinfo - 기준정보 저장", description = "baseinfo - BI_CSYSCD 테이블 저장")
public class BiCsyscdCmdApi {
	private final BiCsyscdCmdCntr csyscdCmdCntr;
	
	@Operation(summary = "baseinfo - 신규생성 Repo", description = "baseinfo - Repository 사용")
	@PostMapping(path = "/savecsyscd")
	public BiCsyscdOutCmdPyld saveCsyscd(@RequestBody BiCsyscdInCmdPyld inPyld) {
		log.info("[Repository]요청 = "+inPyld.toString());
		BiCsyscdOutCmdDto outDs = null;
		
		try {
			BiCsyscdCmdEntt outEntt = csyscdCmdCntr.saveCsyscd(inPyld.getInDs());
			log.info("[Repository]저장 = "+outEntt.getGrpId() + ", "+outEntt.getCd());
			outDs = BiCsyscdOutCmdDto.builder().result("성공").build();
		}
		catch(Exception e) {
			log.error("[Repository]저장 = "+e.getMessage(), e);
			outDs = BiCsyscdOutCmdDto.builder().result("실패").build();
		}
		
		return BiCsyscdOutCmdPyld.builder().outDs(outDs).build();
	}
	
	@Operation(summary = "baseinfo - 신규생성 Anno", description = "baseinfo - Query Annotaion 사용")
	@PostMapping(path = "/insertcsyscd")
	public BiCsyscdOutCmdPyld insertCsyscd(@RequestBody BiCsyscdInCmdPyld inPyld) {
		log.info("[Query Annotaion]요청 = "+inPyld.toString());
		BiCsyscdOutCmdDto outDs = null;
		
		try {
			int result = csyscdCmdCntr.insertCsyscd(inPyld.getInDs());
			log.info("[Query Annotaion]저장 = "+result);
			outDs = BiCsyscdOutCmdDto.builder().result("성공").build();
		}
		catch(Exception e) {
			log.error("[Query Annotaion]저장 = "+e.getMessage(), e);
			outDs = BiCsyscdOutCmdDto.builder().result("실패").build();
		}
		
		return BiCsyscdOutCmdPyld.builder().outDs(outDs).build();
	}
	
	@Operation(summary = "baseinfo - 신규생성 2PC", description = "baseinfo + wrlincomn - 신규생성 2PC")
	@PostMapping(path = "/csyscd2pc")
	public BiCsyscdOutCmdPyld csyscd2pc(@RequestBody BiCsyscdInCmdPyld inPyld) {
		log.info("[Repository]요청 = "+inPyld.toString());
		BiCsyscdOutCmdDto outDs = null;
		
		try {
			int result = csyscdCmdCntr.csyscd2pc(inPyld.getInDs());
			log.info("[2PC]저장 = "+result);
			outDs = BiCsyscdOutCmdDto.builder().result("성공").build();
		}
		catch(Exception e) {
			log.error("[2PC]저장 = "+e.getMessage(), e);
			outDs = BiCsyscdOutCmdDto.builder().result("실패").build();
		}
		
		return BiCsyscdOutCmdPyld.builder().outDs(outDs).build();
	}
	
	@Operation(summary = "baseinfo - 신규생성 async", description = "baseinfo + wrlincomn - 신규생성 async")
	@PostMapping(path = "/csyscdasync")
	public BiCsyscdOutCmdPyld csyscdasync(@RequestBody BiCsyscdInCmdPyld inPyld) {
		log.info("[Repository]요청 = "+inPyld.toString());
		BiCsyscdOutCmdDto outDs = null;
		
		try {
			int result = csyscdCmdCntr.csyscdasync(inPyld.getInDs());
			log.info("[ASYNC]저장 = "+result);
			outDs = BiCsyscdOutCmdDto.builder().result("성공").build();
		}
		catch(Exception e) {
			log.error("[ASYNC]저장 = "+e.getMessage(), e);
			outDs = BiCsyscdOutCmdDto.builder().result("실패").build();
		}
		
		return BiCsyscdOutCmdPyld.builder().outDs(outDs).build();
	}
}
