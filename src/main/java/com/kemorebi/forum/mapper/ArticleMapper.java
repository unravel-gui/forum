package com.kemorebi.forum.mapper;

import com.kemorebi.forum.model.dto.ArticleDTO;
import com.kemorebi.forum.model.dto.ArticleSimDTO;
import com.kemorebi.forum.model.dto.QueryParamDTO;
import com.kemorebi.forum.model.entity.Article;
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
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 查询分页数据true按热度，false按照时间, 都已过审
     * @param status
     * @return
     */
    List<ArticleSimDTO> getIndexArticleSimList(@Param("status") Boolean status);

    /**
     * 根据关键字查询分页数据，true按热度，false按照时间, 都已过审
     * @param dto
     * @param status
     * @return
     */
    List<ArticleSimDTO> getQueryArticleSimList(@Param("dto") QueryParamDTO dto, @Param("status") Boolean status);

    /**
     * 根据文章ID查询文章信息
     * @param aid
     * @return
     */
    ArticleDTO getArticleByAid(@Param("aid") Long aid,@Param("published") Boolean published, @Param("status") Boolean status);

    /**
     * 通过用户ID获得文章ID
     * @param uid
     * @return
     */
    List<ArticleSimDTO> getArticleIdByUid(@Param("uid") Long uid);

    /**
     * 浏览量加一
     * @param aid
     */
    void addPageView(@Param("aid") Long aid);

    /**
     * 点赞数加一
     * @param aid
     */
    void addlikeCount(@Param("aid") Long aid);

    /**
     * 获得文章分页数据
     * @param status 审核状态，null表示无影响
     * @return
     */
    List<ArticleSimDTO> getArticlePage(@Param("status") Boolean status);
}
