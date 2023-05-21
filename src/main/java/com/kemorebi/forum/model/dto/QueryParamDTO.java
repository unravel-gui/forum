package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@ToString
@ApiModel(value = "QueryParamDTO", description = "查询参数类，查询条件之间为and关系，不使用的必须置为空null")
public class QueryParamDTO implements Serializable {
    /**
     * 查询关键字 模糊查询文章标题or文章简介
     */
    @ApiModelProperty("查询关键字")
    private String query;

    /**
     * 分类
     */
    @ApiModelProperty("分类")
    private String type ;

    /**
     * true按热度排序，false按创建时间排序
     */
    @ApiModelProperty("排序方式")
    private Boolean status;
}
