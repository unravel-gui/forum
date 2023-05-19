package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@ApiModel(value = "CommentParamDTO", description = "评论参数传输类")
public class CommenParamDTO implements Serializable {
    @ApiModelProperty("评论ID")
    private Long comId;
    @ApiModelProperty("父评论ID")
    private Long parentComId;
    @ApiModelProperty("评论内容")
    private String Content;
}
