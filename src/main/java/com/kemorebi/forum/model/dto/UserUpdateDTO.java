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
@ApiModel(value = "UserAddDTO", description = "用户新增数据传输类")
public class UserUpdateDTO implements Serializable {

    /**
     * 用户ID
     */
    @NotEmpty(message = "用户ID不能为空")
    @ApiModelProperty("用户ID")
    private Long uid;

    /**
     * 昵称
     */
    @NotEmpty(message = "用户昵称不能为空")
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 年龄
     */
    @Length(min = 0, max = 150, message = "年龄不合法")
    @ApiModelProperty("年龄")
    private Integer age;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String gender;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 手机号
     */
    @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty("手机号")
    private String mobile;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;

    /**
     * 简介
     */
    @Length(max = 255, message = "消息过长")
    @ApiModelProperty("简介")
    private String description;
}
