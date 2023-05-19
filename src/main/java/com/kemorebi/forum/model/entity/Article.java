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
@TableName("forum_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    @TableId(value = "aid", type = IdType.AUTO)
    private Long aid;

    /**
     * 文章标题
     */
    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    /**
     * 文章简介
     */
    @TableField("description")
    private String description;

    /**
     * 文章首图
     */
    @TableField("cover_img")
    private String coverImg;

    /**
     * 是否公开
     */
    @TableField("published")
    private Boolean published;

    /**
     * 是否开启评论
     */
    @TableField("commentabled")
    private Boolean commentabled;

    /**
     * 浏览量
     */
    @TableField("page_view")
    private Long pageView;

    /**
     * 点赞数
     */
    @TableField("like_count")
    private Long likeCount;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 审核状态
     */
    @TableField("status")
    private Boolean status;
}
