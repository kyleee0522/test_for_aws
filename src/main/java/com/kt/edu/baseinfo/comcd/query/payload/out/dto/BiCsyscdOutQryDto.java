package com.kt.edu.baseinfo.comcd.query.payload.out.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kt.edu.baseinfo.comcd.query.repository.entity.BiCsyscdQryEntt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class BiCsyscdOutQryDto {
	private String grpId;
	private String cd;
	private String name;
	private String val1;
	private String val2;
	private String val3;
	private String val4;
	private String val5;	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp regDate;
	
	public static BiCsyscdOutQryDto of(BiCsyscdQryEntt entt) {
		return BiCsyscdOutQryDto.builder()
				.grpId(entt.getGrpId())
				.cd(entt.getGrpId())
				.name(entt.getGrpId())
				.val1(entt.getVal1())
				.val2(entt.getVal2())
				.val3(entt.getVal3())
				.val4(entt.getVal4())
				.val5(entt.getVal5())
				.regDate(entt.getRegDate())
				.build();
	}
}
