package com.kemorebi.forum.service.impl;

import com.kemorebi.forum.model.entity.Comment;
import com.kemorebi.forum.mapper.CommentMapper;
import com.kemorebi.forum.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
