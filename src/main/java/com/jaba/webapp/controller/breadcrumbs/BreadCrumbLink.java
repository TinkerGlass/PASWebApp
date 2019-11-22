package com.jaba.webapp.controller.breadcrumbs;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class BreadCrumbLink implements Serializable {

	private static final long serialVersionUID = -1734182996388561350L;

	private String url;
	private String label;
	private int depth;
	boolean currentPage;

	public BreadCrumbLink(String label, int depth) {
		this.label = label;
		this.depth = depth;
		this.currentPage = currentPage;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public boolean isCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(boolean currentPage) {
		this.currentPage = currentPage;
	}
}
