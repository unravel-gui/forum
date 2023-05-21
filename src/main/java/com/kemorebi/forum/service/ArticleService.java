package com.kemorebi.forum.service;

import com.github.pagehelper.PageInfo;
import com.kemorebi.forum.model.dto.*;
import com.kemorebi.forum.model.entity.Article;
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
public interface ArticleService extends IService<Article> {

    /**
     * 点赞
     * @param aid
     * @return
     */
    Boolean addLikeCount(Long aid);

    /**
     * 浏览量加一
     * @param aid
     * @return
     */
    Boolean addPageView(Long aid);

    /**
     * 根据文章ID获得文章
     * @param aid
     * @param status 表示文章是否审核，null，表示不影响
     * @return
     */
    ArticleDTO getArticleByAid(Long aid, Boolean status);

    /**
     * 根据文章ID获得文章
     * @param aid
     * @param published 表示文章是否公开，null，表示不影响
     * @param status 表示文章是否审核，null，表示不影响
     * @return
     */
    ArticleDTO getArticleByAid(Long aid, Boolean published, Boolean status);

    /**
     * 根据用户ID获得文章基本分页数据
     * @param uid
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<ArticleSimDTO> getArticleListByUid(Long uid, int pageNum, int pageSize);

    /**
     * 保存用户文章
     * @param dto
     * @param uid
     * @return
     */
    boolean saveArticle(ArticleAddDTO dto, Long uid);

    /**
     * 修改用户文章
     * @param dto
     * @return
     */
    boolean updateArticle(ArticleUpdateDTO dto);

    /**
     * 根据热度获得文章类型
     * @param pageNum
     * @param pageSize
     */
    PageInfo getIndexPageHot(int pageNum, int pageSize);

    /**
     * 根据创建时间获得文章类型
     * @param pageNum
     * @param pageSize
     */
    PageInfo getIndexPageTime(int pageNum, int pageSize);

    /**
     * 根据查询参数查询文章
     * @param dto
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getQueryArticle(QueryParamDTO dto, int pageNum, int pageSize);

    /**
     * 根据文章ID删除文章
     * @param aid
     * @return
     */
    Boolean removeArticle(Long aid);

    /**
     * 获得文章分页数据
     * @param status 审核状态，null表示无影响
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getArticlePage(Boolean status, int pageNum, int pageSize);
}
