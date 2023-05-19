package com.kemorebi.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kemorebi.forum.model.entity.User;
import com.kemorebi.forum.mapper.UserMapper;
import com.kemorebi.forum.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 葵gui
 * @since 2023-05-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Boolean checkUserExist(String aoount) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getAccount, aoount);
        return baseMapper.exists(qw);
    }
}
