package com.kemorebi.forum.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 葵gui
 * @since 2023-05-18
 */
@Getter
@Setter
@TableName("forum_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    /**
     * 用户名称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 用户账户
     */
    @TableField("account")
    private String account;

    /**
     * 用户密码
     */
    @TableField("password")
    private String password;

    /**
     * 用户年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 用户性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 用户号码
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 用户头像
     */
    @TableField("avator")
    private String avator;

    /**
     * 用户简介
     */
    @TableField("description")
    private String description;

    /**
     * 是否是管理员
     */
    @TableField("admin")
    private Boolean admin;
}
