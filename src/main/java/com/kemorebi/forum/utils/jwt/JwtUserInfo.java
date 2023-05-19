package com.kemorebi.forum.utils.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * jwt 存储的内容
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtUserInfo implements Serializable {

    /**
     * 账号id
     * */
    private Long userId;

    /**
     * 账号
     * */
    private String account;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 是否是管理员
     */
    private Boolean admin;
}
