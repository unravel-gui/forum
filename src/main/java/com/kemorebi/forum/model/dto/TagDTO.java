package com.kemorebi.forum.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 标签信息传输类
 * @author 葵gui
 * @since 2023-05-18
 */
@Data
@ToString
@ApiModel(value = "TagDTO", description = "标签信息传输类")
public class TagDTO implements Serializable {

    /**
     * 标签ID
     */
    @ApiModelProperty("标签ID")
    private Long tagId;

    /**
     * 标签名称
     */
    @ApiModelProperty("标签名称")
    private String name;
}
