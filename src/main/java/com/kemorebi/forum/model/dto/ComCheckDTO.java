package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@ApiModel(value = "ComCheckDTO", description = "管理员审核展示的评论")
public class ComCheckDTO implements Serializable {
    @ApiModelProperty("评论ID")
    private Long comId;
    @ApiModelProperty("评论者姓名")
    private String comName;
    @ApiModelProperty("评论者头像")
    private String comImg;
    @ApiModelProperty("评论内容")
    private String Content;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
