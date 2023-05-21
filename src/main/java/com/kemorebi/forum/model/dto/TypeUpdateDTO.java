package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
@ApiModel("分类信息")
public class TypeUpdateDTO implements Serializable {

    /**
     * 分类ID
     */
    @NotNull(message = "分类ID不能为空")
    @ApiModelProperty("分类ID")
    private Long typeId;
    /**
     * 分类名称
     */
    @NotEmpty(message = "分类名称不能为空")
    @Length(max = 30, message = "名称长度不能超过30")
    @ApiModelProperty("分类名称")
    private String name;
}
