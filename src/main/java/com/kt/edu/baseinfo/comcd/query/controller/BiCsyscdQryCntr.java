package com.kt.edu.baseinfo.comcd.query.controller;

import java.util.List;

import com.kt.edu.baseinfo.comcd.query.repository.entity.BiCsyscdQryEntt;
import com.kt.edu.baseinfo.comcd.query.payload.in.dto.BiCsyscdInQryDto;
import org.springframework.stereotype.Component;

import com.kt.edu.baseinfo.comcd.query.payload.out.dto.BiCsyscdOutQryDto;
import com.kt.edu.baseinfo.comcd.query.repository.dto.BiCsyscdQryDto;
import com.kt.edu.baseinfo.comcd.query.service.BiCsyscdQrySvc;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BiCsyscdQryCntr {
	private final BiCsyscdQrySvc csyscdQrySvc;
	
	public List<BiCsyscdQryDto> selectCsyscd(BiCsyscdInQryDto inDto) {
		return csyscdQrySvc.selectCsyscd(inDto);
	}
	
	public BiCsyscdOutQryDto findCsyscd(BiCsyscdInQryDto inDto) {
		BiCsyscdQryEntt entt = csyscdQrySvc.findCsyscd(inDto);
		return BiCsyscdOutQryDto.of(entt);
	}
}
