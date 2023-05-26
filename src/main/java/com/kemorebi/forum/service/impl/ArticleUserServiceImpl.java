package com.kemorebi.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kemorebi.forum.model.entity.Article;
import com.kemorebi.forum.model.entity.ArticleUser;
import com.kemorebi.forum.mapper.ArticleUserMapper;
import com.kemorebi.forum.service.ArticleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 葵gui
 * @since 2023-05-20
 */
@Service
public class ArticleUserServiceImpl extends ServiceImpl<ArticleUserMapper, ArticleUser> implements ArticleUserService {

    @Override
    public Long getUidByArticleId(Long aid) {
        LambdaQueryWrapper<ArticleUser> lbq = new LambdaQueryWrapper<>();
        lbq.eq(ArticleUser::getAid, aid);
        ArticleUser au = baseMapper.selectOne(lbq);
        return au!=null ? au.getUid():null;
    }




}
