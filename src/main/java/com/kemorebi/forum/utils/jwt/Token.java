package com.kemorebi.forum.utils.jwt;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * jwt token
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token implements Serializable {

    /**
     * token
     * */
    @ApiModelProperty(value = "token")
    private String token;

    /**
     * 有效时间: 单位: 秒
     * */
    @ApiModelProperty(value = "有效期")
    private Integer expire;
}
