package com.kemorebi.forum.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kemorebi.forum.common.R;
import com.kemorebi.forum.model.dto.LoginDTO;
import com.kemorebi.forum.model.dto.UserDTO;
import com.kemorebi.forum.model.entity.User;
import com.kemorebi.forum.exception.code.ExceptionCode;
import com.kemorebi.forum.service.UserService;
import com.kemorebi.forum.utils.DozerUtils;
import com.kemorebi.forum.utils.jwt.JwtUserInfo;
import com.kemorebi.forum.utils.jwt.Token;
import com.kemorebi.forum.utils.jwt.server.JwtTokenServerUtils;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 认证管理器
 * 实现认证操作
 * */
@Slf4j
@Service
public class AuthManager {

    @Autowired
    private DozerUtils dozerUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenServerUtils jwtTokenServerUtils;

    // 认证成功，为用户生成jwt令牌
//    User user = userR.getData();
//    Token token = generateUserToken(user);
//    UserDTO dto = UserDTO.builder()
//            .account(user.getAccount())
//            .age(user.getAge())
//            .avatar(user.getAccount())
//            .gender(user.getGender())
//            .email(user.getEmail())
//            .description(user.getDescription()).build();


    // 登录认证
    public R<LoginDTO> login(String account, String password) {
        // 校验账号
        R<User> userR = check(account, password);
        Boolean isError = userR.getIsError();
        if (isError) {
            return R.fail("认证失败");
        }

        // 认证成功，为用户生成jwt令牌
        User user = userR.getData();
        Token token = generateUserToken(user);

        // 封装返回结果
        LoginDTO loginDTO = LoginDTO.builder()
                .user(dozerUtils.map(user, UserDTO.class))
                .token(token)
                .build();
        return R.success(loginDTO);
    }

    public R<User> check(String account, String password) {
        // 将前端提交的密码进行md5加密
        String md5HexPass = DigestUtils.md5Hex(password);
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getAccount, account).eq(User::getPassword, md5HexPass));
        // 查不到或者密码不同
        if (user==null) {
            // 认证失败
            return R.fail(ExceptionCode.JWT_USER_INVALID);
        }
        // 认证成功
        return R.success(user);
    }

    // 生成LoginDTO
    public R<LoginDTO> register(String account) {
        // 认证成功，为用户生成jwt令牌
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getAccount, account));
        Token token = generateUserToken(user);


        // 封装返回结果
        LoginDTO loginDTO = LoginDTO.builder()
                .user(dozerUtils.map(user, UserDTO.class))
                .token(token)
                .build();
        return R.success(loginDTO);
    }
    // 为用户生成对应的jwt令牌
    public Token generateUserToken(User user) {
        JwtUserInfo jwtUserInfo = new JwtUserInfo(user.getUid(),
                user.getAccount(),
                user.getMobile(),
                user.getAdmin());
        Token token = jwtTokenServerUtils.generateUserToken(jwtUserInfo, null);
        return token;
    }
}
