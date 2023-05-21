package com.kemorebi.forum.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kemorebi.forum.common.DefaultSetting;
import com.kemorebi.forum.model.dto.CommentAddDTO;
import com.kemorebi.forum.model.dto.CommentDTO;
import com.kemorebi.forum.model.dto.CommentSimDTO;
import com.kemorebi.forum.model.entity.Comment;
import com.kemorebi.forum.mapper.CommentMapper;
import com.kemorebi.forum.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kemorebi.forum.utils.DozerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private DozerUtils dozerUtils;

    @Override
    public List<CommentDTO> getCommentByAid(Long aid, Boolean status) {
        // 获得父子评论集合
        List<CommentDTO> commontList = baseMapper.getCommentByAid(aid, status);
        // 将多层父子结构转换为两层
        List<CommentDTO> resComList = transform(commontList);
        return resComList;
    }

    @Override
    public PageInfo getCommentPage(Boolean status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentSimDTO> dtoList = baseMapper.getCommentPage(status);
        PageInfo<CommentSimDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }


    @Override
    public Boolean addComment(CommentAddDTO dto, Long uid) {
        Comment comment = Comment.builder()
                .aid(dto.getAid())
                .parentCommentId(dto.getParentComId())
                .content(dto.getContent())
                .uid(uid)
                .createTime(LocalDateTime.now())
                .status(DefaultSetting.DEFAULT_COMMENT_STATUS)
                .build();
        baseMapper.insert(comment);
        log.info("添加评论成功: %s", comment);
        return true;
    }

    @Override
    public Boolean canDeleteComment(Long comId, Long uid, Boolean admin) {
        // 管理员
        if (admin) {
            return true;
        }
        // 评论本人，或文章作者
        int count = baseMapper.countCommentForDelete(comId, uid);
        return count>0?true:false;
    }

    @Override
    public Boolean deleteComment(Long comId) {
        // 使用队列删除
        Queue<Long> needToDelete = new LinkedList<>();
        needToDelete.add(comId);
        while (!needToDelete.isEmpty()) {
            // 获得当前待删除节点ID列表
            List<Long> ids = (List<Long>) needToDelete;
            // 删除当前节点
            baseMapper.deleteBatchIds(ids);
            log.info("评论被删除: %s", JSONObject.toJSONString(ids));
            // 查询以删除评论的子评论
            ids = baseMapper.getCommendByPcommentIds(ids);
            needToDelete = new LinkedList<>(ids);
        }
        log.info("评论[%s] 及其子评论删除成功", comId);
        return true;
    }

    @Override
    public Boolean deleteCommentByAid(Long aid) {
        LambdaQueryWrapper<Comment> lbq = new LambdaQueryWrapper<>();
        lbq.eq(Comment::getAid, aid);
        baseMapper.delete(lbq);
        log.info("文章[%s]的评论删除成功", aid);
        return true;
    }

    @Override
    public PageInfo getArticleComment(Long uid, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentSimDTO> dtoList =  baseMapper.getArticleComment(uid);
        PageInfo<CommentSimDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }

    @Override
    public PageInfo getUserComment(Long uid, int pageNum, int pageSize) {
        return getUserComment(uid, pageNum, pageSize, null);
    }

    @Override
    public PageInfo getUserComment(Long uid, int pageNum, int pageSize, Boolean status) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentSimDTO> dtoList =  baseMapper.getUserComment(uid, status);
        PageInfo<CommentSimDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }

    /**
     * 将多层父子结构转换为两层, 根评论及其子评论
     * @param commontList
     * @return
     */
    private List<CommentDTO> transform(List<CommentDTO> commontList) {
        List<CommentDTO> resComList = new ArrayList<>();
        HashMap<Long, CommentDTO> hashMap = new HashMap<>();
        for (CommentDTO dto: commontList) {
            // 先放入hashmap，方便子评论寻找
            hashMap.put(dto.getComId(), dto);
            if (dto.getParentCommentId()==0) {
                // 根评论，入结果数组
                resComList.add(dto);
            } else {
                // 非根评论，必有父评论，有子评论，父评论必定已入hashmap
                // 获得父评论
                CommentDTO parentDTO = hashMap.get(dto.getParentCommentId());
                // 填充子评论中回复对象的昵称属性(pnickname)
                dto.setPnickname(parentDTO.getNickname());
                // 若当前父评论不是根评论则尝试获得根评论
                while (parentDTO.getParentCommentId()!=0) {
                    // 获得父评论
                    parentDTO = hashMap.get(parentDTO.getParentCommentId());
                }
                // 初始化子评论
                if (parentDTO.getSubReply()==null) {
                    parentDTO.setSubReply(new ArrayList<>());
                }
                // 加入根评论的子评论集合
                parentDTO.getSubReply().add(dto);
            }
        }
        return resComList;
    }
}
