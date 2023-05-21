package com.kemorebi.forum.controller;

import com.kemorebi.forum.common.BaseController;
import com.kemorebi.forum.common.R;
import com.kemorebi.forum.model.dto.LoginDTO;
import com.kemorebi.forum.model.dto.LoginParamDTO;
import com.kemorebi.forum.model.dto.RegisterParamDTO;
import com.kemorebi.forum.model.entity.User;
import com.kemorebi.forum.service.UserService;
import com.kemorebi.forum.service.ValidateCodeService;
import com.kemorebi.forum.service.impl.AuthManager;
import com.kemorebi.forum.utils.DozerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录认证控制器
 */
@Slf4j
@RestController
@RequestMapping("/anno")
@Api(tags = "登录控制器", value = "LoginController")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private DozerUtils dozerUtils;
    @Autowired
    private ValidateCodeService validateCodeService;
    @Autowired
    private AuthManager authManager;

    /**
     * 生成验证码
     */
    @GetMapping(value = "/captcha", produces = "image/png")
    @ApiOperation(notes = "验证码", value = "验证码")
    public void captcha(@RequestParam(value = "key") String key, HttpServletResponse response) throws IOException {
        validateCodeService.create(key, response);
    }

    /**
     * 用户注册
     * @param dto
     * @return
     */
    @ApiOperation(notes = "注册", value = "注册")
    @PostMapping("/register")
    public R<LoginDTO> register(@Validated @RequestBody RegisterParamDTO dto) {
        // 校验验证码是否正确
        boolean check = validateCodeService.check(dto.getKey(), dto.getCode());
        if (check) {
            // 判断是否account是否重复
            String aoount = dto.getAccount();
            if (userService.checkUserExist(aoount)) {
                // 存在
                return success(null, "用户已存在");
            }
            // 不存在创建新用户
            dto.setPassword(DigestUtils.md5Hex(dto.getPassword()));
            userService.save(dozerUtils.map(dto, User.class));
            // 获得返回对象
            R<LoginDTO> r = authManager.register(aoount);
            return r;
        }
        // 验证码不正确,
        return success(null, "验证码出错");
    }

    /**
     * 登录操作
     * @param loginParamDTO
     * @return
     */
    @ApiOperation(notes = "登录", value = "登录")
    @PostMapping("/login")
    public R<LoginDTO> login(@Validated @RequestBody LoginParamDTO loginParamDTO) {
        // 校验验证码是否正确
        boolean check = validateCodeService.check(loginParamDTO.getKey(), loginParamDTO.getCode());
        if (check) {
            // 验证码校验通过，执行具体的登录认证逻辑
            R<LoginDTO> r = authManager.login(loginParamDTO.getAccount(), loginParamDTO.getPassword());
            return r;
        }
        // 验证码不正确,
        return success(null, "验证码出错");
    }

    // 校验验证码
    @ApiOperation(notes = "测试校验验证码", value = "校验验证码")
    @PostMapping("/check")
    public boolean check(@RequestBody LoginParamDTO loginParamDTO) {
        return validateCodeService.check(loginParamDTO.getKey(), loginParamDTO.getCode());
    }

    /**
     * 管理员登录操作
     * @param loginParamDTO
     * @return
     */
    @ApiOperation(notes = "登录", value = "登录")
    @PostMapping("/admin/login")
    public R<LoginDTO> adminLogin(@Validated @RequestBody LoginParamDTO loginParamDTO) {
        // 校验验证码是否正确
        boolean check = validateCodeService.check(loginParamDTO.getKey(), loginParamDTO.getCode());
        if (check) {
            // 验证码校验通过，执行具体的登录认证逻辑
            R<LoginDTO> r = authManager.adminLogin(loginParamDTO.getAccount(), loginParamDTO.getPassword());
            return r;
        }
        // 验证码不正确,
        return success(null, "验证码出错");
    }

}
