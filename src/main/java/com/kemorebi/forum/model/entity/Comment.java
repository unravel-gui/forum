package com.kemorebi.forum.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 葵gui
 * @since 2023-05-18
 */
@Builder
@Getter
@Setter
@TableName("forum_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    @TableId(value = "com_id", type = IdType.AUTO)
    private Long comId;

    /**
     * 评论者ID
     */
    @TableField("uid")
    private Long uid;

    /**
     * 文章ID
     */
    @TableField("aid")
    private Long aid;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 评论父ＩＤ
     */
    @TableField("parent_comment_id")
    private Long parentCommentId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 审核状态
     */
    @TableField("status")
    private Boolean status;
}
