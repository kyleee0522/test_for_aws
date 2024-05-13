package com.kt.edu.baseinfo.comcd.command.controller.client.payload.in.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WcCsyscdInCmdDto implements Serializable {
	private static final long serialVersionUID = 8524543457622987583L;
	private String grpId;
	private String cd;
	private String name;
	private String val1;
	private String val2;
	private String val3;
	private String val4;
	private String val5;
}
