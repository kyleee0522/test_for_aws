package com.kt.edu.baseinfo.comcd.command.controller.client.payload.in;

import java.io.Serializable;

import com.kt.edu.baseinfo.comcd.command.controller.client.payload.in.dto.WcCsyscdInCmdDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WcCsyscdInCmdPyld implements Serializable {
	private static final long serialVersionUID = -569268142019708290L;
	private WcCsyscdInCmdDto inDs;
}
