package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Getter
@ApiModel(value = "ArticleUpdateDTO", description = "文章更新传输类")
public class ArticleUpdateDTO implements Serializable {

    /**
     * 文章ID
     */
    @NotNull(message = "不能为空")
    @ApiModelProperty("文章ID")
    private Long aid;

    /**
     * 文章标题
     */
    @NotEmpty(message = "不能为空")
    @ApiModelProperty("文章标题")
    private String title;

    /**
     * 文章内容
     */
    @NotEmpty(message = "不能为空")
    @ApiModelProperty("文章内容")
    private String content;

    /**
     * 文章描述
     */
    @NotEmpty(message = "不能为空")
    @ApiModelProperty("文章描述")
    private String description;

    /**
     * 封面图片
     */
    @ApiModelProperty("封面图片")
    private String coverImg;

    /**
     * 文章是否公开, 默认不公开
     */
    @ApiModelProperty("文章是否公开")
    private Boolean published;

    /**
     * 是否启用评论，默认不开启
     */
    @ApiModelProperty("是否启用评论")
    private Boolean commentabled;

    /**
     * 分类ID
     */
    @NotNull(message = "分类ID不为空")
    @ApiModelProperty("分类ID")
    private Long typeId;


    /**
     * 标签数组
     */
    @ApiModelProperty("标签数组")
    List<Long> tagIds;

}
