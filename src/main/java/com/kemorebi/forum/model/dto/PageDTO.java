package com.kemorebi.forum.model.dto;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@ApiModel(value = "PageDTO", description = "通用分页传输类")
public class PageDTO implements Serializable {
    /**
     * 分页对象
     */
    @ApiModelProperty("分页对象")
    private PageInfo pageInfo;
}
