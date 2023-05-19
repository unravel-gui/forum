package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@ApiModel(value = "QueryParamDTO", description = "查询参数类，查询条件之间为and关系，不使用的必须置为空null")
public class QueryParamDTO implements Serializable {
    /**
     * 查询关键字 模糊查询文章标题or文章简介or文章内容
     */
    @ApiModelProperty("查询关键字")
    private String query;

    /**
     * 类ID
     */
    @ApiModelProperty("类ID")
    private Long typeId;

    /**
     * 标签ID
     */
    @ApiModelProperty("标签ID")
    private Long tagId;

    /**
     * 用户名, 查询用户时，使用这个字段其他为空
     */
    @ApiModelProperty("用户名")
    private String username;
}
