package com.kt.edu.baseinfo.comcd.command.controller;

import java.sql.Timestamp;

import com.kt.edu.baseinfo.comcd.command.controller.client.payload.in.dto.WcCsyscdInCmdDto;
import com.kt.edu.baseinfo.comcd.command.controller.client.payload.out.WcCsyscdOutCmdPyld;
import com.kt.edu.baseinfo.comcd.command.repository.entity.BiCsyscdCmdEntt;
import com.kt.edu.baseinfo.comcd.command.controller.client.payload.in.WcCsyscdInCmdPyld;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kt.edu.baseinfo.comcd.command.controller.client.WcCsyscdCmdClnt;
import com.kt.edu.baseinfo.comcd.command.payload.in.dto.BiCsyscdInCmdDto;
import com.kt.edu.baseinfo.comcd.command.service.BiCsyscdCmdSvc;

//import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class BiCsyscdCmdCntr {
	private final WcCsyscdCmdClnt csyscdCmdClnt;
	private final BiCsyscdCmdSvc csyscdCmdSvc;
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;
	
	@Transactional
	public BiCsyscdCmdEntt saveCsyscd(BiCsyscdInCmdDto inDto) {
		BiCsyscdCmdEntt inEntt = BiCsyscdCmdEntt.builder()
				.grpId(inDto.getGrpId())
				.cd(inDto.getCd())
				.name(inDto.getName())
				.val1(inDto.getVal1())
				.val2(inDto.getVal2())
				.val3(inDto.getVal3())
				.val4(inDto.getVal4())
				.val5(inDto.getVal5())	
				.regDate(new Timestamp(System.currentTimeMillis()))
				.build();
		inEntt.setNew(true);
		
		return csyscdCmdSvc.saveCsyscd(inEntt);	
	}
	
	@Transactional
	public int insertCsyscd(BiCsyscdInCmdDto inDto) {
		BiCsyscdCmdEntt inEntt = BiCsyscdCmdEntt.builder()
				.grpId(inDto.getGrpId())
				.cd(inDto.getCd())
				.name(inDto.getName())
				.val1(inDto.getVal1())
				.val2(inDto.getVal2())
				.val3(inDto.getVal3())
				.val4(inDto.getVal4())
				.val5(inDto.getVal5())	
				.build();
		
		return csyscdCmdSvc.insertCsyscd(inEntt);	
	}

	@Transactional
	public int csyscd2pc(BiCsyscdInCmdDto inDto) {
		int result = insertCsyscd(inDto);
		WcCsyscdInCmdPyld wcInPyld = WcCsyscdInCmdPyld.builder().inDs(WcCsyscdInCmdDto.builder()
								.grpId(inDto.getGrpId())
								.cd(inDto.getCd())
								.name(inDto.getName())
								.val1(inDto.getVal1())
								.val2(inDto.getVal2())
								.val3(inDto.getVal3())
								.val4(inDto.getVal4())
								.val5(inDto.getVal5())
								.build()							
							).build();
		WcCsyscdOutCmdPyld wcOutPyld = csyscdCmdClnt.saveCsyscd(wcInPyld);
		return result;	
	}

	@Transactional
	public int csyscdasync(BiCsyscdInCmdDto inDto) {
		int result = insertCsyscd(inDto);
		WcCsyscdInCmdPyld wcInPyld = WcCsyscdInCmdPyld.builder().inDs(WcCsyscdInCmdDto.builder()
								.grpId(inDto.getGrpId())
								.cd(inDto.getCd())
								.name(inDto.getName())
								.val1(inDto.getVal1())
								.val2(inDto.getVal2())
								.val3(inDto.getVal3())
								.val4(inDto.getVal4())
								.val5(inDto.getVal5())
								.build()							
							).build();
		
		try {
			String json = objectMapper.writeValueAsString(wcInPyld);		
			log.info(json);
			ProducerRecord<String, String> producerRecord = new ProducerRecord<>("test-topic",  json);
			kafkaTemplate.send(producerRecord);
		} 
		catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		return result;	
	}
}
