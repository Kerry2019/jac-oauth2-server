package com.definesys.portal.oauth.jacoauth2server.config;

import com.definesys.portal.oauth.jacoauth2server.handler.TokenEnhancerImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @description:
 * @date: 2021/3/30 2:49 下午
 * @author: kerry
 */
@Configuration
public  class JwtTokenConfig{
    private final String JWT_SIGN_KEY="jwtSigningKey";


    /**
     * 使用jwtTokenStore存储token
     * @return
     */
    @Bean
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 用于生成jwt
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        //生成签名的key
        accessTokenConverter.setSigningKey(JWT_SIGN_KEY);
        return accessTokenConverter;
    }

    /**
     * 用于扩展JWT
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
    public TokenEnhancer jwtTokenEnhancer(){
        return new TokenEnhancerImpl();
    }


}

