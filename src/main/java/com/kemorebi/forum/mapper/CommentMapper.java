package com.kemorebi.forum.mapper;

import com.kemorebi.forum.model.dto.CommentDTO;
import com.kemorebi.forum.model.entity.Comment;
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
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 根据文章ID获得评论
     * @param aid
     * @param status 审核状态， null表示无影响
     * @return
     */
    List<CommentDTO> getCommentByAid(@Param("aid") Long aid, @Param("status") Boolean status);

    /**
     * 查询评论能否被删除，评论本人、作者
     * @param comId
     * @param uid
     * @return 不满足条件返回0，反之返回大于0
     */
    int countCommentForDelete(@Param("comId") Long comId, @Param("uid") Long uid);
    List<Long> getCommendByPcommentIds(@Param("comIds") List<Long> PcomIds);
}
