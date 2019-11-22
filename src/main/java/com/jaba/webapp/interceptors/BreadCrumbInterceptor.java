package com.jaba.webapp.interceptors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jaba.webapp.controller.breadcrumbs.BreadCrumbLink;
import com.jaba.webapp.controller.breadcrumbs.Link;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BreadCrumbInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(!(handler instanceof HandlerMethod))
			return true;
		HttpSession session = request.getSession();
		emptyCurrentBreadCrumb(session);
		processAnnotations(request, session, (HandlerMethod) handler);
		return true;
	}


	private void emptyCurrentBreadCrumb(HttpSession session) {
		session.setAttribute("currentBreadCrumb", new LinkedList<BreadCrumbLink>());
	}


	private void processAnnotations(HttpServletRequest request, HttpSession session, HandlerMethod handler) {
		List<BreadCrumbLink> breadcrumbs = new ArrayList<>();
		List<String> urls = new ArrayList<>();
		BreadCrumbLink currentBreadCrumb = null;

		for(Method m : handler.getMethod().getDeclaringClass().getDeclaredMethods()) {
			String url = null;
			BreadCrumbLink link = null;

			for(Annotation a : m.getDeclaredAnnotations()) {
				if(RequestMapping.class.isAssignableFrom(a.getClass())) {
					url = ((RequestMapping)a).value()[0];
					if(link != null) {
						link.setUrl(url);
						break;
					}
				} else if(a instanceof Link) {
					link = new BreadCrumbLink(((Link) a).label(), ((Link) a).depth());

					if(m.equals(handler.getMethod())) {
						link.setCurrentPage(true);
						currentBreadCrumb = link;
					}

					if(url != null) {
						link.setUrl(url);
						break;
					}
				}
			}

			if(url != null && link != null) {
				if(urls.contains(url)) {
					if(link.isCurrentPage()) {
						int depth = link.getDepth();
						breadcrumbs.removeIf(breadCrumbLink -> breadCrumbLink.getDepth() == depth);
						breadcrumbs.add(link);
					}
				} else {
					breadcrumbs.add(link);
					urls.add(url);
				}
			}

		}


		if(breadcrumbs.size() > 0) {
			breadcrumbs.sort(Comparator.comparing(BreadCrumbLink::getDepth).thenComparing(BreadCrumbLink::isCurrentPage));
			breadcrumbs = breadcrumbs.subList(0, breadcrumbs.indexOf(currentBreadCrumb)+1);
		}
		session.setAttribute("currentBreadCrumb", breadcrumbs);
	}

}
