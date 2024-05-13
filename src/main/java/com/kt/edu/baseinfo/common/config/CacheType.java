package com.kt.edu.baseinfo.common.config;

import lombok.Builder;
import lombok.Getter;

@Getter
public enum CacheType {

    EMPLOYEE("employee", 5 * 60, 10000);

    private String cacheName;
    private int expireAfterWrite;
    private int maximumSize;

    CacheType(String cacheName, int expireAfterWrite, int maximumSize) {
        this.cacheName = cacheName;
        this.expireAfterWrite = expireAfterWrite;
        this.maximumSize = maximumSize;
    }

}
