package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@ApiModel(value = "ArticleDTO", description = "文章传输类")
public class ArticleAddDTO implements Serializable {

    /**
     * 文章标题
     */
    @ApiModelProperty("文章标题")
    private String title;

    /**
     * 文章内容
     */
    @ApiModelProperty("文章内容")
    private String content;

    /**
     * 文章描述
     */
    @ApiModelProperty("文章描述")
    private String description;

    /**
     * 封面图片
     */
    @ApiModelProperty("封面图片")
    private String coverImg;

    /**
     * 文章是否公开
     */
    @ApiModelProperty("文章是否公开")
    private Boolean published;

    /**
     * 是否启用评论
     */
    @ApiModelProperty("是否启用评论")
    private Boolean commentabled;

}
