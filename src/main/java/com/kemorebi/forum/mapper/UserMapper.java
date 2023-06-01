package com.kemorebi.forum.mapper;

import com.kemorebi.forum.model.dto.UserSimDTO;
import com.kemorebi.forum.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 葵gui
 * @since 2023-05-18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<UserSimDTO> queryUser(@Param("query") String query);
}
