package com.kemorebi.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kemorebi.forum.common.DefaultSetting;
import com.kemorebi.forum.model.dto.*;
import com.kemorebi.forum.model.entity.*;
import com.kemorebi.forum.mapper.ArticleMapper;
import com.kemorebi.forum.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kemorebi.forum.utils.DozerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleUserService articleUserService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private ArticleTypeService articleTypeService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private DozerUtils dozerUtils;

    @Override
    public Boolean addLikeCount(Long aid) {
        baseMapper.addlikeCount(aid);
        return true;
    }

    @Override
    public ArticleDTO getArticleByAid(Long aid, Boolean status) {
        return getArticleByAid(aid, null, status);
    }

    @Override
    public ArticleDTO getArticleByAid(Long aid, Boolean published, Boolean status) {
        ArticleDTO dto = baseMapper.getArticleByAid(aid, published, status);
        if (dto!=null) {
            fillArticleWithTags(dto);
        }
        return dto;
    }

    @Override
    public PageInfo<ArticleSimDTO> getArticleListByUid(Long uid, int pageNum, int pageSize) {
        // 获得该用户的文章ID
        PageHelper.startPage(pageNum, pageSize);
        // 根据用户ID，获得文章
        List<ArticleSimDTO> simDTOList =  baseMapper.getArticleIdByUid(uid);
        fillArticleListWithTags(simDTOList);
        PageInfo<ArticleSimDTO> pageInfo = new PageInfo<>(simDTOList);
        return pageInfo;
    }

    @Override
    public Boolean addPageView(Long aid) {
        baseMapper.addPageView(aid);
        return true;
    }

    @Override
    public boolean saveArticle(ArticleAddDTO dto, Long uid) {
        Article article = dozerUtils.map(dto, Article.class);
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        article.setLikeCount(new Long(0));
        article.setPageView(new Long(0));
        article.setCommentabled(dto.getCommentabled()==null?DefaultSetting.DEFAULT_COMMENTABLED:dto.getCommentabled());
        article.setPublished(dto.getPublished()==null?DefaultSetting.DEFAULT_PUBLISHED:dto.getPublished());
        // 默认为通过审核
        article.setStatus(DefaultSetting.DEFAULT_ARTICLE_STATUS);
        // 保存文章信息
        save(article);
        log.info("文章[%s]保存成功", article.getAid());
        // 建立文章和用户之间的映射关系
        ArticleUser au = ArticleUser.builder()
                .uid(uid)
                .aid(article.getAid())
                .build();
        au.setUid(uid);
        au.setAid(article.getAid());
        articleUserService.save(au);
        log.info("uid[%s]与文章[%s]成功建立映射", au.getUid(), au.getAid());
        // 建立文章和分类关系
        ArticleType atp = ArticleType.builder()
                .aid(article.getAid())
                .typeId(dto.getTypeId()==null?1:dto.getTypeId())
                .build();
        articleTypeService.save(atp);
        log.info("文章[%s]与分类[%s]成功建立映射", atp.getAid(),atp.getTypeId());
        // 建立文章和标签之间的关系
        List<Long> tagIds = dto.getTagIds();
        if (tagIds != null) {
            for (Long tagId: tagIds) {
                ArticleTag articleTag = ArticleTag.builder()
                        .aid(article.getAid())
                        .tagId(tagId).build();
                articleTagService.save(articleTag);
                log.info("文章[%s]与标签[%s]成功建立映射", articleTag.getAid(), articleTag.getTagId());
            }
        }
        log.info("uid[%s]提交文章[%s]成功,文章标题 [%s]", uid, article.getAid(), article.getTitle());
        return true;
    }

    @Override
    public boolean updateArticle(ArticleUpdateDTO dto) {
        // 修改文章信息
        LambdaUpdateWrapper<Article> lbq = new LambdaUpdateWrapper<>();
        lbq.eq(Article::getAid, dto.getAid())
                .set(Article::getTitle, dto.getTitle())
                .set(Article::getContent, dto.getContent())
                .set(Article::getDescription, dto.getDescription())
                .set(Article::getCoverImg, dto.getCoverImg())
                // 默认不公开
                .set(Article::getPublished, dto.getPublished()==null?false:dto.getPublished())
                // 默认不开启
                .set(Article::getCommentabled, dto.getCommentabled()==null?false:dto.getCommentabled())
                .set(Article::getUpdateTime, LocalDateTime.now());
        update(lbq);
        // 修改文章与分类映射关系
        LambdaUpdateWrapper<ArticleType> lbuatp = new LambdaUpdateWrapper<>();
        lbuatp.eq(ArticleType::getAid, dto.getAid())
                .set(ArticleType::getTypeId, dto.getTypeId());
        articleTypeService.update(lbuatp);
        // 修改文章与标签映射关系
        // 删除文章原来的标签
        LambdaQueryWrapper<ArticleTag> lbqat = new LambdaQueryWrapper<>();
        lbqat.eq(ArticleTag::getAid, dto.getAid());
        articleTagService.remove(lbqat);
        // 添加新的标签
        List<Long> tagIds = dto.getTagIds();
        for (Long tagId: tagIds) {
            ArticleTag at = ArticleTag.builder()
                    .aid(dto.getAid())
                    .tagId(tagId)
                    .build();
            articleTagService.save(at);
        }
        log.info("文章[%s] 被用户修改", dto.getAid());
        return true;
    }

    @Override
    public PageInfo getIndexPageHot(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleSimDTO> simDTOList = baseMapper.getIndexArticleSimList(true);
        fillArticleListWithTags(simDTOList);
        PageInfo<ArticleSimDTO> pageInfo = new PageInfo<>(simDTOList);
        return pageInfo;
    }

    @Override
    public PageInfo getIndexPageTime(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleSimDTO> simDTOList = baseMapper.getIndexArticleSimList(false);
        fillArticleListWithTags(simDTOList);
        PageInfo<ArticleSimDTO> pageInfo = new PageInfo<>(simDTOList);
        return pageInfo;
    }

    @Override
    public PageInfo getQueryArticle(QueryParamDTO dto, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleSimDTO> simDTOList = baseMapper.getQueryArticleSimList(dto, dto.getStatus()==null?true:dto.getStatus());
        fillArticleListWithTags(simDTOList);
        PageInfo<ArticleSimDTO> pageInfo = new PageInfo<>(simDTOList);
        return pageInfo;
    }

    @Override
    public Boolean removeArticle(Long aid) {
        // 删除文章
        removeById(aid);
        // 删除用户与文章映射关系
        LambdaQueryWrapper<ArticleUser> lbq = new LambdaQueryWrapper<>();
        lbq.eq(ArticleUser::getAid, aid);
        articleUserService.remove(lbq);
        // 删除文章与类型映射关系
        LambdaQueryWrapper<ArticleType> lbqtp = new LambdaQueryWrapper<>();
        lbqtp.eq(ArticleType::getAid, aid);
        articleTypeService.remove(lbqtp);
        // 删除文章与标签映射关系
        LambdaQueryWrapper<ArticleTag> lbqtg = new LambdaQueryWrapper<>();
        lbqtg.eq(ArticleTag::getAid, aid);
        articleTagService.remove(lbqtg);
        // 删除文章评论
        commentService.deleteCommentByAid(aid);
        log.info("文章[%s]信息及其映射关系删除成功", aid);
        return true;
    }

    @Override
    public PageInfo getArticlePage(Boolean status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleSimDTO> dtoList = baseMapper.getArticlePage(status);
        PageInfo<ArticleSimDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }

    /**
     * 给分页文章数据添加标签信息
     * @param list
     */
    void fillArticleListWithTags(List<ArticleSimDTO> list) {
        for (ArticleSimDTO dto: list) {
            fillArticleWithTags(dto);
        }
    }

    private void fillArticleWithTags(ArticleSimDTO dto) {
        List<TagDTO> tags = tagService.getTagListByAid(dto.getAid());
        dto.setTags(tags);
    }
    private void fillArticleWithTags(ArticleDTO dto) {
        List<TagDTO> tags = tagService.getTagListByAid(dto.getAid());
        dto.setTags(tags);
    }
}
