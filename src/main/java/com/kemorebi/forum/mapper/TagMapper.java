package com.kemorebi.forum.mapper;

import com.kemorebi.forum.model.dto.TagDTO;
import com.kemorebi.forum.model.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.naming.Name;
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
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 通过文章ID获得标签
     * @param aid
     * @return
     */
    List<TagDTO> getTagListByAid(@Param("aid") Long aid);

    /**
     * 计算用户标签数量
     * @param uid
     * @return
     */
    Integer countTagByUid(@Param("uid") Long uid);
}
