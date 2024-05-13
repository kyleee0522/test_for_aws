package com.kt.edu.baseinfo.comcd.query.payload.out;

import java.util.List;

import com.kt.edu.baseinfo.comcd.query.repository.dto.BiCsyscdQryDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BiCsyscdOutListQryPyld {
	private List<BiCsyscdQryDto> outListDs;
}
