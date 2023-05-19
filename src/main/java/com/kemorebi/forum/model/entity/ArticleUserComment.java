package com.kemorebi.forum.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("forum_article_user_comment")
public class ArticleUserComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章ID
     */
    @TableField("aid")
    private Long aid;

    /**
     * 用户ID
     */
    @TableField("uid")
    private Long uid;

    /**
     * 评论ID
     */
    @TableField("comid")
    private Long comid;
}
