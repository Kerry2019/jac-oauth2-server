package com.definesys.portal.oauth.jacoauth2server.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

/**
 * @description:
 * @date: 2021/3/30 2:47 下午
 * @author: kerry
 */
@Component("permitAllSecurityConfig")
public class PermitAllSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final Filter permitAuthenticationFilter;

    public PermitAllSecurityConfig(Filter permitAuthenticationFilter) {
        this.permitAuthenticationFilter = permitAuthenticationFilter;
    }

    @Override
    public void configure(HttpSecurity http)  {
        http.addFilterBefore(permitAuthenticationFilter, OAuth2AuthenticationProcessingFilter.class);
    }
}
