package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@ApiModel(value = "CommentAddDTO", description = "新增评论的传输类")
public class CommentAddDTO implements Serializable {

    /**
     * 文章ID
     */
    @NotNull(message = "文章ID不能为空")
    @ApiModelProperty("文章ID")
    private Long aid;

    /**
     * 父评论ID
     */
    @NotNull(message = "父评论ID不能为空")
    @ApiModelProperty("父评论ID")
    private Long parentComId;

    /**
     * 评论内容
     */
    @NotEmpty(message = "评论ID不能为空")
    @Length(max = 255, message = "评论内容过长")
    @ApiModelProperty("评论内容")
    private String Content;
}
