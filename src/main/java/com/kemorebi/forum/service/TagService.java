package com.kemorebi.forum.service;

import com.github.pagehelper.PageInfo;
import com.kemorebi.forum.model.dto.TagAddDTO;
import com.kemorebi.forum.model.dto.TagDTO;
import com.kemorebi.forum.model.dto.TagUpdateDTO;
import com.kemorebi.forum.model.entity.Tag;
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
public interface TagService extends IService<Tag> {

    /**
     * 通过文章ID会的Tag标签
     * @param aid
     * @return
     */
    List<TagDTO> getTagListByAid(Long aid);

    /**
     * 根据用户ID获得Tag标签
     * @param uid
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getTagListByUid(Long uid, int pageNum, int pageSize);

    /**
     * 判断标签是否已存在
     * @param uid
     * @param name
     * @return
     */
    Boolean isTagExist(Long uid, String name);

    /**
     * 新增标签
     * @param dto
     * @param uid
     * @return
     */
    Boolean saveTag(TagAddDTO dto, Long uid);

    /**
     * 通过标签ID和用户ID 删除标签
     * @param tagId
     * @param uid
     * @return
     */
    Boolean removeTag(Long tagId, Long uid);

    /**
     * 是否达到用户标签数量的上限
     * @param uid
     * @return
     */
    Boolean isTagMax(Long uid);
}
