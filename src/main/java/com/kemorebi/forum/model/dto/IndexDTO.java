package com.kemorebi.forum.model.dto;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@ApiModel(value = "IndexDTO", description = "首页数据传输类")
public class IndexDTO implements Serializable {

    /**
     * 所有分类信息
     */
    @ApiModelProperty("所有分类信息")
    private List<TypeDTO> typeList;

    /**
     * 分页文章信息
     */
    @ApiModelProperty("分页文章信息")
    private PageInfo page;
}
