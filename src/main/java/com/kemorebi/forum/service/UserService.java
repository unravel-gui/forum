package com.kemorebi.forum.service;

import com.kemorebi.forum.model.dto.UserSimDTO;
import com.kemorebi.forum.model.dto.UserUpdateDTO;
import com.kemorebi.forum.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 葵gui
 * @since 2023-05-18
 */
public interface UserService extends IService<User> {
    public Boolean checkUserExist(String aoount);

    public Boolean updateUser(UserUpdateDTO dto);


    List<UserSimDTO> queryUser(String query);

}
