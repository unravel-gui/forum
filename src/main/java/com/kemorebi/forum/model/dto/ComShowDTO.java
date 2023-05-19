package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@ApiModel(value = "ComShowDTO", description = "前端展示的评论")
public class ComShowDTO implements Serializable {
    /**
     * 用户评论
     */
    @ApiModelProperty("用户评论")
    private List<CommentShowDTO> subReply;
}
