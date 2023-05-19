package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "UserDTO", description = "用户信息传输类")
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
     * 性别
     */
    @ApiModelProperty("性别")
    private String gender;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;

    /**
     * 简介
     */
    @ApiModelProperty("简介")
    private String description;

}
