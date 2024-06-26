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

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CachingRequestWrapper extends HttpServletRequestWrapper {

    private Charset encoding;
    private byte[] rawData;

    public CachingRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        String characterEncoding = request.getCharacterEncoding();
        if (StringUtils.isEmpty(characterEncoding)) {
            characterEncoding = StandardCharsets.UTF_8.name();
        }
        this.encoding = Charset.forName(characterEncoding);

        try (InputStream inputStream = request.getInputStream()) {
            this.rawData = IOUtils.toByteArray(inputStream);
        }
    }

    @Override
    public ServletInputStream getInputStream() {
        return new CachedServletInputStream(this.rawData);
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), this.encoding));
    }

    private static class CachedServletInputStream extends ServletInputStream {

        private ByteArrayInputStream buffer;

        public CachedServletInputStream(byte[] contents) {
            this.buffer = new ByteArrayInputStream(contents);
        }

        @Override
        public int read() throws IOException {
            return buffer.read();
        }

        @Override
        public boolean isFinished() {
            return buffer.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            throw new UnsupportedOperationException("not support");
        }
    }
}
