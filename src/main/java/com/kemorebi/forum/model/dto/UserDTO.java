package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@ApiModel(value = "UserDTO", description = "用户信息传输类")
public class UserDTO  implements Serializable {

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
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String gender;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobile;

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
