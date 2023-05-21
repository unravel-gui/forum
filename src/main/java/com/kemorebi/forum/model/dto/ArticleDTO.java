package com.kemorebi.forum.model.dto;

import com.kemorebi.forum.model.entity.Tag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@ApiModel(value = "ArticleDTO", description = "文章传输类")
public class ArticleDTO implements Serializable {

    /**
     * 文章ID
     */
    @ApiModelProperty("文章ID")
    private Long aid;

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

    /**
     * 浏览量
     */
    @ApiModelProperty("浏览量")
    private Long pageView;
    /**
     * 点赞数
     */
    @ApiModelProperty("点赞数")
    private Long likeCount;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private String type;

    /**
     * 标签
     */
    @ApiModelProperty("标签")
    private List<TagDTO> tags;

}
