package com.kemorebi.forum.service;

import com.kemorebi.forum.model.entity.Article;
import com.kemorebi.forum.model.entity.ArticleUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 葵gui
 * @since 2023-05-20
 */
public interface ArticleUserService extends IService<ArticleUser> {

    /**
     * 通过文章ID获得用户ID
     * @param aid
     * @return
     */
    Long getUidByArticleId(Long aid);

}
