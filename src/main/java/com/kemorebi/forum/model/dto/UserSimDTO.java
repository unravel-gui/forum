package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "UserSimDTO", description = "用户信息传输类")
public class UserSimDTO implements Serializable {

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long uid;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 账户
     */
    @ApiModelProperty("账户")
    private String account;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String gender;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avator;

    /**
     * 简介
     */
    @ApiModelProperty("简介")
    private String description;

}
