package com.java.jd.config;

import com.java.jd.bean.CacheConfig;
import com.java.jd.bean.FieldBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class BeanConfig {

    @Bean
    CacheConfig getCacheConfig(){
        FieldBean fieldBean = new FieldBean();
        fieldBean.setField("name");
        fieldBean.setValues(Arrays.asList("A", "B", "C"));

        CacheConfig cacheConfig = new CacheConfig();
        cacheConfig.setClassName("com.java.jd.config.BeanConfig");
        cacheConfig.setClazz(CacheConfig.class);
        cacheConfig.setObjName("user");
        cacheConfig.setFieldBeans(Arrays.asList(fieldBean));
        return cacheConfig;
    }

    @Bean
    CacheConfig getCacheConfig2(){
        FieldBean fieldBean = new FieldBean();
        fieldBean.setField("age");
        fieldBean.setValues(Arrays.asList("1", "2", "3"));

        CacheConfig cacheConfig = new CacheConfig();
        cacheConfig.setClassName("com.java.jd.config.BeanConfig");
        cacheConfig.setClazz(CacheConfig.class);
        cacheConfig.setObjName("person");
        cacheConfig.setFieldBeans(Arrays.asList(fieldBean));
        return cacheConfig;
    }
}
