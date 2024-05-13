package com.kt.edu.baseinfo.common.utils;

import com.kt.edu.baseinfo.common.context.ApplicationContextProvider;


public class CommonBeanUtil {

    public static Object getBean(String beanName) {
        try {
            return ApplicationContextProvider.getContext().getBean(beanName);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> Object getBean(Class<T> clz) {
        return ApplicationContextProvider.getContext().getBean(clz);

    }

}
