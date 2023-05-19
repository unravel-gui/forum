package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@ToString
@ApiModel(value = "RegisterParamDTO", description = "注册参数传输类")
public class RegisterParamDTO implements Serializable {

    /**
     * 验证码key
     */
    @NotEmpty(message = "验证码key不能为空")
    @ApiModelProperty("验证码key")
    private String key;
    /**
     * 验证码
     */
    @NotEmpty(message = "验证码不能为空")
    @ApiModelProperty("验证码")
    private String code;


    /**
     * 邀请码
     */
//    @NotEmpty(message = "邀请码不能为空")
    @ApiModelProperty("邀请码")
    private String inviteCode;

    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不能为空")
    @ApiModelProperty("昵称")
    private String nickname;


    /**
     * 账户
     */
    @NotEmpty(message = "账户不能为空")
    @ApiModelProperty("账户")
    @Length(min = 5,max = 16, message = "账号长度应该在5到16之间")
    private String account;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, message = "账号长度应该大于6")
    @ApiModelProperty("密码")
    private String password;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private String age;

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
    @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty("手机号")
    private String mobile;

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
