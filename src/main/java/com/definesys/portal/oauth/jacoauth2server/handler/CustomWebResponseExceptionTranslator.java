package com.definesys.portal.oauth.jacoauth2server.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @date: 2021/3/30 2:46 下午
 * @author: kerry
 */
@Component("customWebResponseExceptionTranslator")
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) {
        CustomOAuth2Exception customOauthException=new CustomOAuth2Exception(e.getCause().getMessage());
        return ResponseEntity
                .status(customOauthException.getHttpErrorCode())
                .body(customOauthException);
    }
}
