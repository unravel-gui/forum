package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@ToString
@ApiModel(value = "LoginParamDTO", description = "登录参数传输类")
public class LoginParamDTO implements Serializable {
    /**
     * 登录账户
     */
    @NotEmpty(message = "账户不能为空")
    @ApiModelProperty("登录账户")
    private String account;
    /**
     * 登录密码
     */
    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty("登陆密码")
    private String password;
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
}
