package com.definesys.portal.oauth.jacoauth2server.pojo;

import lombok.Data;

/**
 * @description:
 * @date: 2021/3/30 3:45 下午
 * @author: kerry
 */
@Data
public class UserProfileDTO {
    /**
     * 工号
     */
    private String name;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 昵称
     */
    private String nickname;
}
