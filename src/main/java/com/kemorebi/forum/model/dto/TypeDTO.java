package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@ApiModel("分类信息")
public class TypeDTO implements Serializable {

    /**
     * 分类ID
     */
    @ApiModelProperty("分类ID")
    private Long typeId;
    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    private String name;
}
