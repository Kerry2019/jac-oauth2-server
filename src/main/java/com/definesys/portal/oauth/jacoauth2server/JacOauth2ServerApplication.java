package com.definesys.portal.oauth.jacoauth2server;

import com.definesys.portal.oauth.jacoauth2server.pojo.LdapDataProperty;
import com.definesys.portal.oauth.jacoauth2server.pojo.LdapSecurityProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableConfigurationProperties({LdapSecurityProperty.class, LdapDataProperty.class})
@EnableResourceServer
@SpringBootApplication
public class JacOauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JacOauth2ServerApplication.class, args);
    }

}
