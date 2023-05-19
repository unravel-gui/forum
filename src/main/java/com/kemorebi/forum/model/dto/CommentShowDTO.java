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
@ApiModel(value = "CommentShowDTO", description = "评论传输类")
public class CommentShowDTO implements Serializable {
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
    @ApiModelProperty("回复了谁，父评论")
    private String replyName;
    @ApiModelProperty("子评论")
    private List<CommentShowDTO> subReply;
}
