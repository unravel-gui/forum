package com.kemorebi.forum.service;

import com.github.pagehelper.PageInfo;
import com.kemorebi.forum.model.dto.CommentAddDTO;
import com.kemorebi.forum.model.dto.CommentDTO;
import com.kemorebi.forum.model.entity.Comment;
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
public interface CommentService extends IService<Comment> {
    /**
     * 根据文章ID查询评论
     * @param aid
     * @param status 是否过审核, null表示不影响
     * @return
     */

    List<CommentDTO> getCommentByAid(Long aid, Boolean status);

    /**
     * 查询评论分页数据
     * @param status 审核状态，null表示无影响
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getCommentPage(Boolean status, int pageNum, int pageSize);

    /**
     * 添加评论
     * @param dto
     * @param uid
     * @return
     */
    Boolean addComment(CommentAddDTO dto, Long uid);

    /**
     * 判断能否删除该评论，允许本人、作者、管理员删除
     * @param comId 评论ID
     * @param uid   删除操作者ID
     * @param admin 是否是管理员
     * @return
     */
    Boolean canDeleteComment(Long comId, Long uid, Boolean admin);

    /**
     * 根据评论ID删除评论及其子评论
     * @param comId
     * @return
     */
    Boolean deleteComment(Long comId);

    /**
     * 根据文章ID删除评论
     * @param aid
     * @return
     */
    Boolean deleteCommentByAid(Long aid);

    /**
     * 根据用户ID获得用户文章的所有评论
     * @param uid
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getArticleComment(Long uid, int pageNum, int pageSize);

    /**
     * 获得用户的所有评论
     * @param uid
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getUserComment(Long uid, int pageNum, int pageSize);

    /**
     *  获得用户的所有评论
     * @param uid
     * @param pageNum
     * @param pageSize
     * @param status 评论审核状态，null表示无影响
     * @return
     */
    PageInfo getUserComment(Long uid, int pageNum, int pageSize, Boolean status);
}
