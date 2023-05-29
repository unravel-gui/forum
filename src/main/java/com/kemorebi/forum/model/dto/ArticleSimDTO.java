package com.kemorebi.forum.model.dto;

import com.kemorebi.forum.model.entity.Tag;
import com.kemorebi.forum.model.entity.Type;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@ApiModel(value = "AricleSimDTO", description = "简单文章信息传输类")
public class ArticleSimDTO implements Serializable {
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
     * 审核状态
     */
    @ApiModelProperty("审核状态")
    private Boolean status;

    /**
     * 分类
     */
    @ApiModelProperty("分类")
    private String type;

    /**
     * 标签
     */
    @ApiModelProperty("标签")
    private List<TagDTO> tags;

    /**
     * 作者
     */
    @ApiModelProperty("作者")
    private String author;

    /**
     * 作者头像
     */
    @ApiModelProperty("作者头像")
    private String uavator;

}
