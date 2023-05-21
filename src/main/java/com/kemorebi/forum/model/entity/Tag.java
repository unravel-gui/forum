package com.kemorebi.forum.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

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
@TableName("forum_tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  标签ID
     */
    @TableId(value = "tag_id", type = IdType.AUTO)
    private Long tagId;

    /**
     * 用户ID
     */
    @TableField("uid")
    private Long uid;

    /**
     * 标签名称
     */
    @TableField("name")
    private String name;
}
