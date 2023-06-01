package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@ApiModel(value = "CommonDTO", description = "前端展示的评论")
public class CommentDTO implements Serializable {

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
     * 评论者名称
     */
    @ApiModelProperty("评论者名称")
    private String nickname;

    /**
     * 评论者头像
     */
    @ApiModelProperty("uavator")
    private String uavator;

    /**
     * 评论内容
     */
    @ApiModelProperty("content")
    private String content;

    /**
     * 评论父ID
     */
    @ApiModelProperty("parent_comment_id")
    private Long parentCommentId;

    /**
     * 评论者名称
     */
    @ApiModelProperty("pnickname")
    private String pnickname;

    /**
     * 创建时间
     */
    @ApiModelProperty("create_time")
    private LocalDateTime createTime;

    /**
     * 审核状态
     */
    @ApiModelProperty("status")
    private Boolean status;

    /**
     * 子评论
     */
    @ApiModelProperty("子评论")
    private List<CommentDTO> subReply;
}
