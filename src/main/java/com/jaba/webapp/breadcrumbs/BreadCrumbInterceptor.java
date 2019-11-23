package com.jaba.webapp.breadcrumbs;

import com.jaba.webapp.breadcrumbs.annotation.Breadcrumb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BreadCrumbInterceptor extends HandlerInterceptorAdapter {

    private BreadcrumbMap breadcrumbMap;
    private static final Logger log = LoggerFactory.getLogger(BreadCrumbInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod))
            return true;
        HttpSession session = request.getSession();
        emptyCurrentBreadCrumb(session);
        putCurrentBreadCrumb(session, (HandlerMethod) handler);
        return true;
    }

    private void emptyCurrentBreadCrumb(HttpSession session) {
        session.setAttribute("currentBreadCrumb", null);
    }

    private void putCurrentBreadCrumb(HttpSession session, HandlerMethod handler) {
        Breadcrumb handlerBreadCrumb = handler.getMethodAnnotation(Breadcrumb.class);
        if(handlerBreadCrumb == null)
            return;
        String family = handlerBreadCrumb.family()[0];
        int depth = handlerBreadCrumb.depth();
        List<BreadCrumb> pathBreadcrumbs = breadcrumbMap.getBreadCrumbMap().get(family);

        log.debug("Mapped handler to breadcrumb family {} with depth {}", family, depth);

        List<BreadCrumb> currentBreadCrumbs = new ArrayList<>();
        for(BreadCrumb breadCrumb : pathBreadcrumbs) {
            currentBreadCrumbs.add(breadCrumb);
            if(breadCrumb.getDepth() >= depth)
                break;
        }

        session.setAttribute("currentBreadCrumb", currentBreadCrumbs);
    }

    @Autowired
    public void setBreadcrumbMap(BreadcrumbMap breadcrumbMap) {
        this.breadcrumbMap = breadcrumbMap;
    }
}
