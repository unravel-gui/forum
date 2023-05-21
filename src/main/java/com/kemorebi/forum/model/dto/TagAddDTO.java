package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 标签信息传输类
 * @author 葵gui
 * @since 2023-05-18
 */
@Getter
@Setter
@ApiModel(value = "TagTO", description = "标签信息传输类")
public class TagAddDTO implements Serializable {

    /**
     * 标签名称
     */
    @NotEmpty(message = "标签名称不能为空")
    @Length(max = 30, message = "名称长度不能超过30")
    @ApiModelProperty("标签名称")
    private String name;
}
