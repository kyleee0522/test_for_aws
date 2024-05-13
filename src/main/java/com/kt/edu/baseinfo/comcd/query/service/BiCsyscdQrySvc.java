package com.kt.edu.baseinfo.comcd.query.service;

import java.util.List;

import com.kt.edu.baseinfo.comcd.query.payload.in.dto.BiCsyscdInQryDto;
import com.kt.edu.baseinfo.comcd.query.repository.BiCsyscdQryMbtsRepo;
import com.kt.edu.baseinfo.comcd.query.repository.BiCsyscdQryRepo;
import com.kt.edu.baseinfo.comcd.query.repository.dto.BiCsyscdQryDto;
import com.kt.edu.baseinfo.comcd.query.repository.entity.BiCsyscdQryEntt;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BiCsyscdQrySvc {
	private final BiCsyscdQryMbtsRepo csyscdQryMbtsRepo;
	private final BiCsyscdQryRepo csyscdQryRepo;
	
	public List<BiCsyscdQryDto> selectCsyscd(BiCsyscdInQryDto inDto) {
		return csyscdQryMbtsRepo.selectCsyscd(inDto);
	}
	public BiCsyscdQryEntt findCsyscd(BiCsyscdInQryDto inDto) {
		return csyscdQryRepo.findByGrpIdAndCd(inDto.getGrpId(), inDto.getCd());
	}
}
