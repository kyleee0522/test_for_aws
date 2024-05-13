/************************************************************************************** 
* ICIS version 1.0
 *
 * Copyright ⓒ 2022 kt/ktds corp. All rights reserved.
 *
 * This is a proprietary software of kt corp, and you may not use this file except in compliance
 * with license agreement with kt corp. Any redistribution or use of this software, with or without
 * modification shall be strictly prohibited without prior written approval of kt corp, and the
 * copyright notice above does not evidence any actual or intended publication of such software.
 *************************************************************************************/
package com.kt.edu.baseinfo.common.wrapper;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kt.edu.baseinfo.common.payload.CommonHeader;
import com.kt.edu.baseinfo.common.utils.CommonUtil;
import com.kt.edu.baseinfo.common.utils.JsonUtil;
import com.kt.edu.baseinfo.common.utils.LogUtil;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.MDC;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class RequestBodyTransformWrapper extends HttpServletRequestWrapper {
  private String transformBody;

  private ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Constructs a request object wrapping the given request.
   *
   * @param request The request to wrap
   * @throws IllegalArgumentException if the request is null
   */
  public RequestBodyTransformWrapper(HttpServletRequest request) {
    super(request);

    byte[] rawData = null;
    String modifRequestBody = "";
    String modifRequestHeader = request.getHeader("commonHeader");

    try {
      InputStream inputStream = request.getInputStream();
      rawData = IOUtils.toByteArray(inputStream);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
    try {

      StringBuffer retVal = new StringBuffer();
      
      //////////////////////////
      String requestBody = new String(rawData, StandardCharsets.UTF_8);
      ////////////////////////

      ObjectMapper mapper = new ObjectMapper();

      // String reqBody = new String(rawData);
      LogUtil.info("[CMMN-FRWK] reqBody: {}", requestBody);

      String globalNo = "00000000000000000000000000000000";
      CommonHeader commonHeader =null;
      if (modifRequestHeader !=null) {
         commonHeader = JsonUtil.jsonToObject(modifRequestHeader, "", new TypeReference<CommonHeader>() {
        });
      }
      //CommonHeader commonHeader  = CommonUtil.getReqCommonHeader(modifRequestHeader);

      List<String> payloadNameList = CommonUtil.getReqPayloadNames(requestBody);

      if (commonHeader != null) {
          log.info("================ APIGW 호출 =====================");
          request.setAttribute("commonHeader", commonHeader);

            retVal.append("{");
            for (int i = 0; i < payloadNameList.size(); i++) {
              String payloadName = payloadNameList.get(i);
              // Object obj = JsonPath.parse(reqBody).read("$.service_request." + payloadName);
              Object obj = mapper.readTree(requestBody).at("/service_request/" + payloadName);
              retVal.append(String.format("\"%s\":%s", payloadName, mapper.writeValueAsString(obj)));
              if (i < payloadNameList.size() - 1) {
                retVal.append(",");
              }
            }
            retVal.append("}");

          globalNo = commonHeader.getGlobalNo();

      }

      MDC.put("globalNo", globalNo);
      
      if(request.getRequestURI().contains("swagger-ui") || 
          request.getRequestURI().contains("system") ||
          request.getRequestURI().contains("actuator") ||
          request.getRequestURI().contains("api-docs") ) {
    	  // Do not send BMON
//        log.info(">>> req uri:{}", request.getRequestURI());
      }


    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    LogUtil.info("===[CMMN-FRWK] DECRYPTION retVal: {}", modifRequestBody);
    this.transformBody = modifRequestBody;
  }

  @Override
  public ServletInputStream getInputStream() {
    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(transformBody.getBytes(StandardCharsets.UTF_8));
    return new ServletInputStream() {
      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setReadListener(ReadListener listener) {}

      @Override
      public int read() {
        return byteArrayInputStream.read();
      }
    };
  }

  @Override
  public BufferedReader getReader() {
    return new BufferedReader(new InputStreamReader(this.getInputStream()));
  }
}
