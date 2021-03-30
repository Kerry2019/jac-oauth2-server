package com.definesys.portal.oauth.jacoauth2server.server;

import com.definesys.portal.oauth.jacoauth2server.handler.ClientDetailsServiceImpl;
import com.definesys.portal.oauth.jacoauth2server.handler.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @date: 2021/3/30 1:51 下午
 * @author: kerry
 */
@Configuration
@EnableAuthorizationServer
public class CustomAuthorizationServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final TokenStore tokenStore;
    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final TokenEnhancer jwtTokenEnhancer;
    private final WebResponseExceptionTranslator customWebResponseExceptionTranslator;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final ClientDetailsServiceImpl clientDetailsServiceImpl;

    public CustomAuthorizationServerConfigurerAdapter(AuthenticationManager authenticationManager, TokenStore tokenStore, JwtAccessTokenConverter jwtAccessTokenConverter, TokenEnhancer jwtTokenEnhancer, WebResponseExceptionTranslator customWebResponseExceptionTranslator, UserDetailsServiceImpl userDetailsServiceImpl, ClientDetailsServiceImpl clientDetailsServiceImpl) {
        this.authenticationManager = authenticationManager;
        this.tokenStore = tokenStore;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.jwtTokenEnhancer = jwtTokenEnhancer;
        this.customWebResponseExceptionTranslator = customWebResponseExceptionTranslator;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.clientDetailsServiceImpl = clientDetailsServiceImpl;
    }

    /**
     * 严重token
     *
     * 1、allowFormAuthenticationForClients()：授权码模式中获取token时，可以直接将client_id、client_secret作为参数传入
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    /**
     * 客户端 client
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .withClientDetails(clientDetailsServiceImpl)
                .clients(clientDetailsServiceImpl);
    }


    /**
     * 用户token扩展
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsServiceImpl);

        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancerList = new ArrayList();
            enhancerList.add(jwtTokenEnhancer);
            enhancerList.add(jwtAccessTokenConverter);
            tokenEnhancerChain.setTokenEnhancers(enhancerList);
            endpoints.tokenEnhancer(tokenEnhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }
        endpoints.exceptionTranslator(customWebResponseExceptionTranslator);
    }


}
