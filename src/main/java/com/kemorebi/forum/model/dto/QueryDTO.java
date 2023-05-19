package com.kemorebi.forum.model.dto;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@ApiModel(value = "QueryDTO", description = "查询返回传输类")
public class QueryDTO implements Serializable {

    @ApiModelProperty("查询到的分页信息")
    private PageInfo page;

}
