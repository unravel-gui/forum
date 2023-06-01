package com.kemorebi.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.kemorebi.forum.model.dto.UserSimDTO;
import com.kemorebi.forum.model.dto.UserUpdateDTO;
import com.kemorebi.forum.model.entity.User;
import com.kemorebi.forum.mapper.UserMapper;
import com.kemorebi.forum.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 葵gui
 * @since 2023-05-18
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Boolean checkUserExist(String aoount) {
        LambdaQueryWrapper<User> lbq = new LambdaQueryWrapper<>();
        lbq.eq(User::getAccount, aoount);
        return baseMapper.exists(lbq);
    }

    @Override
    public Boolean updateUser(UserUpdateDTO dto) {
        LambdaUpdateWrapper<User> lbu = new LambdaUpdateWrapper<>();
        lbu.eq(User::getUid, dto.getUid())
                .set(User::getNickname, dto.getNickname())
                .set(User::getMobile, dto.getMobile());
        if (dto.getAge()!= null) {
            lbu.set(User::getAge, dto.getAge());
        }

        if (dto.getGender() != null) {
            lbu.set(User::getGender, dto.getGender());
        }

        if (dto.getEmail() != null) {
            lbu.set(User::getEmail, dto.getEmail());
        }

        if (dto.getAvatar() != null) {
            lbu.set(User::getAvator, dto.getAvatar());
        }

        if (dto.getDescription() != null) {
            lbu.set(User::getDescription, dto.getDescription());
        }
        update(lbu);
        log.info("用户[%s] 信息修改成功, 修改参数为: %s", dto.getUid(), dto);
        return true;
    }

    @Override
    public List<UserSimDTO> queryUser(String query) {
        return baseMapper.queryUser(query);
    }
}
