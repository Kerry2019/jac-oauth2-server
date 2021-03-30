package com.definesys.portal.oauth.jacoauth2server.handler;

import com.definesys.portal.oauth.jacoauth2server.dao.PaasMapper;
import com.definesys.portal.oauth.jacoauth2server.pojo.OauthClientEO;
import com.definesys.portal.oauth.jacoauth2server.util.EncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @description:
 * @date: 2021/3/30 2:12 下午
 * @author: kerry
 */
@Component
public class ClientDetailsServiceImpl implements ClientDetailsService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PaasMapper paasMapper;

    public ClientDetailsServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, PaasMapper paasMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.paasMapper = paasMapper;
    }

    @Override
    public BaseClientDetails loadClientByClientId(String clientId)  {
        OauthClientEO client = paasMapper.getClientByClientId(clientId);
        if (client == null) {
            throw new ClientRegistrationException(clientId+"无效") ;
        }
        BaseClientDetails baseClientDetails=new BaseClientDetails();
        baseClientDetails.setClientId(client.getClientId());
        baseClientDetails.setClientSecret(EncoderUtil.encryptByBCrypt(client.getClientSecret()));
        baseClientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
        baseClientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
        baseClientDetails.setAuthorizedGrantTypes(Arrays.asList(client.getAuthorizedGrantTypes().split(",")));
        Collection<SimpleGrantedAuthority> collection = new HashSet<>();
        collection.add(new SimpleGrantedAuthority("ROLE_USER"));
        baseClientDetails.setAuthorities(collection);
        Set<String> scope = new TreeSet<String>();
        scope.add("user_info");
        baseClientDetails.setScope(scope);
        baseClientDetails.setAutoApproveScopes(scope);

        return baseClientDetails;
    }
}
