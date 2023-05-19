package com.kemorebi.forum.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
