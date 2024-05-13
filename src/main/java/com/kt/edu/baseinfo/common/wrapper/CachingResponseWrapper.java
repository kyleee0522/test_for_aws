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

import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.util.FastByteArrayOutputStream;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponseWrapper;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

public class CachingResponseWrapper extends HttpServletResponseWrapper {

    private static final FastByteArrayOutputStream content = new FastByteArrayOutputStream(1024);
    private ServletOutputStream outputStream;
    private PrintWriter writer;
  
    public CachingResponseWrapper(HttpServletResponse response) {
      super(response);
    }
  
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
      if (this.outputStream == null) {
        this.outputStream = new CachedServletOutputStream(getResponse().getOutputStream(), this.content);
      }
      return this.outputStream;
    }
  
    @Override
    public PrintWriter getWriter() throws IOException {
      if (writer == null) {
        writer = new PrintWriter(new OutputStreamWriter(content, this.getCharacterEncoding()), true);
      }
      return writer;
    }
  
    public InputStream getContentInputStream() {
      return this.content.getInputStream();
    }
  
    private class CachedServletOutputStream extends ServletOutputStream {
  
      private TeeOutputStream targetStream;
  
      public CachedServletOutputStream(OutputStream one, OutputStream two) {
        targetStream = new TeeOutputStream(one, two);
      }
  
      @Override
      public void write(int arg) throws IOException {
        this.targetStream.write(arg);
      }
  
      @Override
      public void write(byte[] buf, int off, int len) throws IOException {
        this.targetStream.write(buf, off, len);
      }
  
      @Override
      public void flush() throws IOException {
        super.flush();
        this.targetStream.flush();
      }
  
      @Override
      public void close() throws IOException {
        super.close();
        this.targetStream.close();
      }
  
      @Override
      public boolean isReady() {
        return false;
      }
  
      @Override
      public void setWriteListener(WriteListener writeListener) {
        throw new UnsupportedOperationException("not support");
      }
    }
  }