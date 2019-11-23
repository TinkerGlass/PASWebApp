package com.jaba.webapp.breadcrumbs;

import com.jaba.webapp.breadcrumbs.annotation.Breadcrumb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

public class BreadcrumbMap {
    private Map<String, List<BreadCrumb>> breadCrumbMap;
    private static final Logger log = LoggerFactory.getLogger(BreadcrumbMap.class);

    public void createMap() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));

        breadCrumbMap = new HashMap<>();
        for(BeanDefinition beanDefinition : scanner.findCandidateComponents("com.jaba.webapp.controller")) {
            try {
                processController(Class.forName(beanDefinition.getBeanClassName()));
            } catch(ClassNotFoundException e) {
                log.warn(MessageFormat.format( "Failed to map breadcrumbs in class {}", beanDefinition.getBeanClassName()), e);
            }
        }

        postProcessMap();
    }

    private void processController(Class clazz) {
        List<BreadCrumb> breadcrumbs = new ArrayList<>();

        for(Method m : clazz.getDeclaredMethods()) {
            String[] families = null;
            String url = null;
            BreadCrumb breadCrumb = null;

            for(Annotation a : m.getDeclaredAnnotations()) {
                if(a instanceof RequestMapping) {
                    url = ((RequestMapping)a).value()[0];

                    if(breadCrumb != null) {
                        breadCrumb.setUrl(url);
                        break;
                    }
                } else if(a instanceof Breadcrumb) {
                    breadCrumb = new BreadCrumb(((Breadcrumb) a).label(), ((Breadcrumb) a).depth());
                    families = ((Breadcrumb) a).family();

                    if(url != null) {
                        breadCrumb.setUrl(url);
                        break;
                    }
                }
            }

            if(url != null && breadCrumb != null) {
                for(String family : families) {
                    if(!breadCrumbMap.containsKey(family))
                        breadCrumbMap.put(family, new ArrayList<>());

                    int depth = breadCrumb.getDepth();
                    breadCrumbMap.get(family).removeIf(b -> b.getDepth() == depth);
                    breadCrumbMap.get(family).add(breadCrumb);
                    log.debug("Mapped breadcrumb url {} to breadcrumb label {} with depth {} in family {}", breadCrumb.getUrl(), breadCrumb.getLabel(), depth, family);
                }
            }
        }
    }

    private void postProcessMap() {
        for(Map.Entry<String, List<BreadCrumb>> familyMap : breadCrumbMap.entrySet()) {
            familyMap.getValue().sort(Comparator.comparing(BreadCrumb::getDepth));
            log.debug("Sorted breadcrumbs in family {} in the following order: {}", familyMap.getKey(),
                    String.join(", ", familyMap.getValue().stream().map(b -> b.getLabel()).collect(Collectors.toList())));
        }
    }

    public Map<String, List<BreadCrumb>> getBreadCrumbMap() {
        return breadCrumbMap;
    }
}
