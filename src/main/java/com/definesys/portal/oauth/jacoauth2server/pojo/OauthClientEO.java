package com.definesys.portal.oauth.jacoauth2server.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @date: 2021/3/30 2:14 下午
 * @author: kerry
 */
@Data
public class OauthClientEO {
    /**
     * 客户端编号
     */
    private String clientId;
    /**
     * 客户端密钥
     */
    private String clientSecret;
    /**
     * 授权方式
     */
    private String authorizedGrantTypes;
    /**
     * 重定向地址
     */
    private String webServerRedirectUri;
    /**
     * access_token 有效期
     */
    private int accessTokenValidity;
    /**
     * refresh_token 有效期
     */
    private int refreshTokenValidity;
    /**
     * client客户端的描述内容
     */
    private String clientDescription;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy.MM.dd HH:mm:ss",timezone = "GMT+8")
    private Date creationDate;
    /**
     * 附加字段
     */
    @Transient
    private List<String> authorizedGrantTypesList;

}
