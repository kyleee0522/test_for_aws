package com.kt.edu.baseinfo.comcd.command.controller.client;

import com.kt.edu.baseinfo.comcd.command.controller.client.payload.in.WcCsyscdInCmdPyld;
import com.kt.edu.baseinfo.comcd.command.controller.client.payload.out.WcCsyscdOutCmdPyld;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "WcCsyscdCmdClnt", url = "http://localhost:8090/wrlincomn", fallbackFactory = WcCsyscdCmdClntFallbackFactory.class)
public interface WcCsyscdCmdClnt {
	@PostMapping(value = "/comcd/savecsyscd")
    WcCsyscdOutCmdPyld saveCsyscd(@RequestBody WcCsyscdInCmdPyld inPyld);
}
