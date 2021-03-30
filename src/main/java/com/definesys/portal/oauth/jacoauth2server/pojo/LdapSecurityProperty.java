package com.definesys.portal.oauth.jacoauth2server.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @date: 2021/3/30 2:19 下午
 * @author: kerry
 */
@Data
@ConfigurationProperties("custom.ldap.security")
public class LdapSecurityProperty {
    private String searchBase;
    private String searchFilter;
    private String url;
    private String manageDn;
    private String managePassword;
}
