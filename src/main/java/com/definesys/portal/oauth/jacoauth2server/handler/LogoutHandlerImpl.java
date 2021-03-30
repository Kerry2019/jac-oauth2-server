package com.definesys.portal.oauth.jacoauth2server.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @date: 2021/3/30 2:36 下午
 * @author: kerry
 */
@Component
public class LogoutHandlerImpl implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            final String refererUrl = request.getHeader("Referer");
            response.sendRedirect(refererUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
