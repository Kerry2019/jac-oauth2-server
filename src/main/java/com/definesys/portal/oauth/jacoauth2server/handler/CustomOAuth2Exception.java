package com.definesys.portal.oauth.jacoauth2server.handler;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @description:
 * @date: 2021/3/30 2:46 下午
 * @author: kerry
 */
@JsonSerialize(using = CustomOAuth2ExceptionSerializer.class)
public class CustomOAuth2Exception extends OAuth2Exception {
    public CustomOAuth2Exception(String msg) {
        super(msg);
    }
}
