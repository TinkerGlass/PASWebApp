package com.jaba.webapp.breadcrumbs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = LoggerFactory.getLogger(StartupApplicationListener.class);

    private BreadcrumbMap breadcrumbMap;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Initializing breadcrumb map");
        breadcrumbMap.createMap();
    }

    @Autowired
    public void setBreadcrumbMap(BreadcrumbMap breadcrumbMap) {
        this.breadcrumbMap = breadcrumbMap;
    }
}
