package com.jaba.webapp.controller.breadcrumbs;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Link {

	public String label();
	public int depth();
}
