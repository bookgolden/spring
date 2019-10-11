package com.java.jd.comp;

import com.java.jd.bean.CacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    private List<CacheConfig> list;

    @Override

    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(list.toString());
        System.out.println("ApplicationListener.onApplicationEvent..." + event);
    }

}
