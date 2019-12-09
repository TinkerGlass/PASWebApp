package com.jaba.webapp.configuration;

import com.jaba.webapp.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


public class LoginSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.inMemoryAuthentication()
                .withUser("jduser").password("jdu@123").authorities(User.AccountType.CLIENT.toString())
                .and()
                .withUser("jdadmin").password("jda@123").authorities(User.AccountType.ADMINISTRATOR.toString())
                .and()
                .withUser("jbal").password("jda@123").authorities(User.AccountType.RESOURCE_MANAGER.toString());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers("/items").access("hasRole('CLIENT') or hasRole('ADMINISTRATOR')")
                .antMatchers("/userPage").access("hasRole('RESOURCE_MANAGER')")
                .antMatchers("/adminPage").access("hasRole('RESOURCE_MANAGER')")
                .and()
                .formLogin().loginPage("/home")
                .defaultSuccessUrl("/items")
                .failureUrl("/clients")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/loginPage?logout");

    }
}
