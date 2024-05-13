package com.kt.edu.baseinfo.comcd.command.controller.client.payload.out;

import com.kt.edu.baseinfo.comcd.command.controller.client.payload.out.dto.WcCsyscdOutCmdDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WcCsyscdOutCmdPyld {
	private WcCsyscdOutCmdDto outDs;
}
