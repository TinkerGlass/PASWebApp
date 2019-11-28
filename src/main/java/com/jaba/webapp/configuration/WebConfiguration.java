package com.jaba.webapp.configuration;

import com.jaba.webapp.breadcrumbs.BreadCrumbInterceptor;
import com.jaba.webapp.breadcrumbs.BreadcrumbMap;
import com.jaba.webapp.converter.*;
import com.jaba.webapp.formatter.*;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.jaba.webapp")
public class WebConfiguration implements WebMvcConfigurer {

    private ApplicationContext applicationContext;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(breadCrumbInterceptor());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(accountTypeFormatter());
        //registry.addFormatter(dateFormatter());
        registry.addFormatter(stickerFormatter());
        registry.addFormatter(albumGenreFormatter());
        registry.addFormatter(videoGenreFormatter());

        registry.addConverter(userConverter());
        registry.addConverter(userStringConverter());
        //registry.addConverter(dateConverter());

        registry.addConverter(new ItemStringConverter());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/html/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean
    public AccountTypeFormatter accountTypeFormatter() {
        return new AccountTypeFormatter();
    }
    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }
    @Bean
    public FanStickerFormatter stickerFormatter() {
        return new FanStickerFormatter();
    }
    @Bean
    public AlbumGenreFormatter albumGenreFormatter() {
        return new AlbumGenreFormatter();
    }
    @Bean
    public VideoGenreFormatter videoGenreFormatter() {
        return new VideoGenreFormatter();
    }

    @Bean
    public UserConverter userConverter() {
        return new UserConverter();
    }
    @Bean
    public UserStringConverter userStringConverter() {
        return new UserStringConverter();
    }
    @Bean
    public DateConverter dateConverter() {
        return new DateConverter();
    }

    @Bean
    public BreadcrumbMap getBreadCrumbMap() {
        return new BreadcrumbMap();
    }
    @Bean
    public BreadCrumbInterceptor breadCrumbInterceptor() {return new BreadCrumbInterceptor();}

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
