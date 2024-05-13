package com.kt.edu.baseinfo.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@RefreshScope
public class PropertyUtil {

    @Value("${cmmnfwrk.logging.show:false}")
    public String cmmnfwrkLoggingShow;

    @Value("${cmmnfwrk.api.safedb.agent.url:}")
    public String safeDbAgentUrl;

    @Value("${app-info.node-ip:}")
    public String nodeIp;


}