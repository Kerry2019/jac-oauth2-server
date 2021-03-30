package com.definesys.portal.oauth.jacoauth2server.dao;

import com.definesys.portal.oauth.jacoauth2server.pojo.OauthClientEO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @date: 2021/3/30 2:23 下午
 * @author: kerry
 */
@Component
@Mapper
public interface PaasMapper {

    /**
     * 通过clientId 查询 client
     *
     * @param clientId
     * @return
     */
    OauthClientEO getClientByClientId(@Param("clientId") String clientId);

    /**
     * 获取当前登录页
     * @return
     */
    String getConfigPage();
}
