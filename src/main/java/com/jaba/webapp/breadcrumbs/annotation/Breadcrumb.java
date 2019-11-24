package com.jaba.webapp.breadcrumbs.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Breadcrumb {
    public String label();
    public int depth();
    public String[] family();
}
