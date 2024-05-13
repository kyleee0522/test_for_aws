package com.kt.edu.baseinfo.comcd.command.payload.out;

import com.kt.edu.baseinfo.comcd.command.payload.out.dto.BiCsyscdOutCmdDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BiCsyscdOutCmdPyld {
	private BiCsyscdOutCmdDto outDs;
}
