package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@ApiModel(value = "CommonDTO", description = "前端展示的评论")
public class CommentSimDTO implements Serializable {

    /**
     * 评论ID
     */
    @ApiModelProperty("评论ID")
    private Long comId;

    /**
     * 评论者ID
     */
    @ApiModelProperty("评论者ID")
    private Long uid;

    /**
     * 文章ID
     */
    @ApiModelProperty("文章ID")
    private Long aid;

    /**
     * 评论内容
     */
    @ApiModelProperty("评论内容")
    private String content;


    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 审核状态
     */
    @ApiModelProperty("审核状态")
    private Boolean status;
}
