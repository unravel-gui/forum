package com.kemorebi.forum.service;

import com.kemorebi.forum.model.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 葵gui
 * @since 2023-05-18
 */
public interface TypeService extends IService<Type> {
    /**
     * 根据分类ID删除分类，以及对应映射
     * @param typeId
     * @return
     */
    Boolean removeType(Long typeId);

    /**
     * 判断分类是否已存在
     * @param name
     * @return
     */
    Boolean isTypeExist(String name);

}
