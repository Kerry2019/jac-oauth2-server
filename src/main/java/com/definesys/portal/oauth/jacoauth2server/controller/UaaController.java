package com.definesys.portal.oauth.jacoauth2server.controller;

import com.definesys.portal.oauth.jacoauth2server.dao.PaasMapper;
import com.definesys.portal.oauth.jacoauth2server.pojo.UserProfileDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @description:
 * @date: 2021/3/30 3:44 下午
 * @author: kerry
 */
@Controller
@RequestMapping(value = "/uaa")
public class UaaController {
    private final String JWT_SIGN_KEY="jwtSigningKey";
    private final PaasMapper paasMapper;
    private final LdapTemplate ldapTemplate;

    public UaaController(PaasMapper paasMapper, LdapTemplate ldapTemplate) {
        this.paasMapper = paasMapper;
        this.ldapTemplate = ldapTemplate;
    }

    /**
     * 自定义登录页面
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return paasMapper.getConfigPage();
    }

    /**
     * 解析token，获取用户信息
     * @param authentication
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @GetMapping("/parseJwt")
    public UserProfileDTO getCurrentUser1(Authentication authentication, HttpServletRequest request) throws UnsupportedEncodingException {
        String header = request.getHeader("Authorization");
        String token = header
                .replace("bearer ","")
                .replace("Bearer ","");
        Claims claims = Jwts.parser().setSigningKey(JWT_SIGN_KEY.getBytes("UTF-8")).parseClaimsJws(token).getBody();
        String username=(String) claims.get("user_name");
        UserProfileDTO userProfileDTO=new UserProfileDTO();
        userProfileDTO.setName(username);
        return userProfileDTO;
    }

    /**
     * ldap 登录
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @GetMapping("/open/ldap-login")
    public Boolean openLdapLogin(@RequestParam("username") String username,
                                 @RequestParam("password")String password){
        EqualsFilter filter = new EqualsFilter("cn",username);
        return ldapTemplate.authenticate("",filter.toString(),password);
    }

}