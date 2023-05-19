package com.kemorebi.forum.model.dto;

import com.kemorebi.forum.utils.jwt.Token;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
@ApiModel(value = "LoginDTO", description = "登录信息")
public class LoginDTO implements Serializable {
    @ApiModelProperty(value = "用户信息")
    private UserDTO user;
    @ApiModelProperty(value = "token")
    private Token token;
}