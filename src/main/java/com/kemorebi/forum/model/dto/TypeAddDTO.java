package com.kemorebi.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@ToString
@ApiModel("分类信息")
public class TypeAddDTO implements Serializable {

    /**
     * 分类名称
     */
    @NotEmpty(message = "分类名称不能为空")
    @Length(max = 30, message = "名称长度不能超过30")
    @ApiModelProperty("分类名称")
    private String name;
}
