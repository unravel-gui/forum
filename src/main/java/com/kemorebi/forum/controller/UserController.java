package com.kemorebi.forum.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageInfo;
import com.kemorebi.forum.common.BaseController;
import com.kemorebi.forum.common.R;
import com.kemorebi.forum.exception.BizException;
import com.kemorebi.forum.exception.code.ExceptionCode;
import com.kemorebi.forum.model.dto.ArticleAddDTO;
import com.kemorebi.forum.model.dto.PageDTO;
import com.kemorebi.forum.model.dto.UserDTO;
import com.kemorebi.forum.model.dto.UserUpdateDTO;
import com.kemorebi.forum.model.entity.Article;
import com.kemorebi.forum.model.entity.User;
import com.kemorebi.forum.service.ArticleService;
import com.kemorebi.forum.service.ArticleUserService;
import com.kemorebi.forum.service.UserService;
import com.kemorebi.forum.utils.DozerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 葵gui
 * @since 2023-05-18
 */
@Slf4j
@Api(tags = "用户控制器", value = "提供用户信息操作")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 获得单个用户信息
     * @return
     */
    @GetMapping()
    @ApiOperation("获得用户信息")
    public R<User> getUser() {
        Long userId = getUserId();
        User user = userService.getById(userId);
        return success(user);
    }


    /**
     * 更新用户信息,并返回更新后的用户信息
     * 只允许本人和管理员进行操作
     * @param dto
     * @return
     */
    @PutMapping
    @ApiOperation("更新用户信息")
    public R<User> updateUser(@Validated @RequestBody UserUpdateDTO dto) {
        Long userId = getUserId();
        // uid对不上并且不是管理员
        if (dto.getUid()!=userId&&!getAdmin()) {
            return fail(new BizException(ExceptionCode.UNAUTHORIZED.getCode(), "禁止水平越权"));
        }
        // 更新用户
        userService.updateUser(dto);
        // 获得更新后的用户信息
        User user = userService.getById(dto.getUid());
        return success(user);
    }


}
