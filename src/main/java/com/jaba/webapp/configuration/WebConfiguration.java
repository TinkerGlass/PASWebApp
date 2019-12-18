package com.jaba.webapp.configuration;

import com.jaba.webapp.breadcrumbs.BreadCrumbInterceptor;
import com.jaba.webapp.breadcrumbs.BreadcrumbMap;
import com.jaba.webapp.converter.StringToAccountTypeConverter;
import com.jaba.webapp.converter.StringToDateConverter;
import com.jaba.webapp.converter.StringToItemConverter;
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
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
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
        registry.addFormatter(stickerFormatter());
        registry.addFormatter(albumGenreFormatter());
        registry.addFormatter(videoGenreFormatter());

        registry.addConverter(itemStringConverter());
        registry.addConverter(accountTypeConverter());
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
        templateEngine.addDialect(springSecurityDialect());
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
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }


    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
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
    public StringToDateConverter dateConverter() {
        return new StringToDateConverter();
    }
    @Bean
    public StringToAccountTypeConverter accountTypeConverter() { return new StringToAccountTypeConverter(); }
    @Bean
    public StringToItemConverter itemStringConverter() { return new StringToItemConverter(); }

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
