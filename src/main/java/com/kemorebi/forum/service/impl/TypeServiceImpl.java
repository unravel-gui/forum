package com.kemorebi.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kemorebi.forum.common.DefaultSetting;
import com.kemorebi.forum.model.entity.ArticleType;
import com.kemorebi.forum.model.entity.Tag;
import com.kemorebi.forum.model.entity.Type;
import com.kemorebi.forum.mapper.TypeMapper;
import com.kemorebi.forum.service.ArticleTypeService;
import com.kemorebi.forum.service.TypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Autowired
    private ArticleTypeService articleTypeService;

    @Override
    public Boolean removeType(Long typeId) {
        // 删除分类
        LambdaQueryWrapper<Type> lbq = new LambdaQueryWrapper<>();
        lbq.eq(Type::getTypeId, typeId);
        baseMapper.delete(lbq);
        // 删除分类和文章的映射关系
        LambdaQueryWrapper<ArticleType> lbqat = new LambdaQueryWrapper<>();
        lbqat.eq(ArticleType::getTypeId, typeId);
        articleTypeService.remove(lbqat);
        log.info("分类[%s]及其映射关系删除成功", typeId);
        return true;
    }

    @Override
    public Boolean isTypeExist(String name) {
        LambdaQueryWrapper<Type> lbq = new LambdaQueryWrapper<>();
        lbq.eq(Type::getName, name);
        return baseMapper.exists(lbq);
    }
}
