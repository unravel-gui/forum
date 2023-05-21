package com.kemorebi.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kemorebi.forum.common.DefaultSetting;
import com.kemorebi.forum.model.dto.TagAddDTO;
import com.kemorebi.forum.model.dto.TagDTO;
import com.kemorebi.forum.model.entity.ArticleTag;
import com.kemorebi.forum.model.entity.Tag;
import com.kemorebi.forum.mapper.TagMapper;
import com.kemorebi.forum.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kemorebi.forum.utils.DozerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private ArticleTagServiceImpl articleTagService;
    @Autowired
    private DozerUtils dozerUtils;

    @Override
    public List<TagDTO> getTagListByAid(Long aid) {
        return baseMapper.getTagListByAid(aid);
    }

    @Override
    public PageInfo getTagListByUid(Long uid, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        LambdaQueryWrapper<Tag> lbq = new LambdaQueryWrapper<>();
        lbq.eq(Tag::getUid, uid);
        List<Tag> tags = baseMapper.selectList(lbq);
        List<TagDTO> dtoss = dozerUtils.mapList(tags, TagDTO.class);
        PageInfo<TagDTO> pageInfo = new PageInfo<>(dtoss);
        return pageInfo;
    }

    @Override
    public Boolean isTagExist(Long uid, String name) {
        LambdaQueryWrapper<Tag> lbq = new LambdaQueryWrapper<>();
        lbq.eq(Tag::getUid, uid)
                .eq(Tag::getName, name);
        return baseMapper.exists(lbq);
    }

    @Override
    public Boolean saveTag(TagAddDTO dto, Long uid) {
        // 添加标签
        Tag tag = Tag.builder()
                .name(dto.getName())
                .uid(uid)
                .build();
        baseMapper.insert(tag);
        log.info("标签[%s]【%s】添加成功成功", tag.getTagId(), tag.getName());
        return true;
    }

    @Override
    public Boolean removeTag(Long tagId, Long uid) {
        // 删除标签
        LambdaQueryWrapper<Tag> lbq = new LambdaQueryWrapper<>();
        lbq.eq(Tag::getTagId, tagId)
                .eq(Tag::getUid, uid);
        baseMapper.delete(lbq);
        // 删除标签和文章的映射关系
        LambdaQueryWrapper<ArticleTag> lbqat = new LambdaQueryWrapper<>();
        lbqat.eq(ArticleTag::getTagId, tagId);
        articleTagService.remove(lbqat);
        log.info("标签[%s]及其映射关系 删除成功", tagId);
        return true;
    }
    @Override
    public Boolean isTagMax(Long uid) {
        Integer count = baseMapper.countTagByUid(uid);
        return count>= DefaultSetting.DEFAULT_TAG_MAXSIZE;
    }
}
