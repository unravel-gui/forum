package com.kemorebi.forum.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.kemorebi.forum.common.BaseController;
import com.kemorebi.forum.common.R;
import com.kemorebi.forum.exception.BizException;
import com.kemorebi.forum.exception.code.ExceptionCode;
import com.kemorebi.forum.model.dto.PageDTO;
import com.kemorebi.forum.model.dto.TypeAddDTO;
import com.kemorebi.forum.model.dto.TypeDTO;
import com.kemorebi.forum.model.dto.TypeUpdateDTO;
import com.kemorebi.forum.model.entity.Type;
import com.kemorebi.forum.service.TypeService;
import com.kemorebi.forum.utils.DozerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 葵gui
 * @since 2023-05-18
 */
@Slf4j
@Api(tags = "分类控制器", value = "提供分类操作")
@RestController
@RequestMapping("/type")
public class TypeController extends BaseController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private DozerUtils dozerUtils;

    /**
     * 获得用户分类信息
     * @param typeId
     * @return
     */
    @GetMapping("/{typeId}")
    @ApiOperation("获得用户分类信息")
    public R<TypeDTO> getType(@PathVariable("typeId") Long typeId) {
        if (typeId==null) {
            return fail("缺少分类ID参数");
        }
        // 管理员鉴权
        if (getAdmin()) {
            Type type = typeService.getById(typeId);
            TypeDTO dto = dozerUtils.map(type, TypeDTO.class);
            return success(dto);
        }
        // 权限不足
        return fail(new BizException(ExceptionCode.UNAUTHORIZED.getCode(), ExceptionCode.UNAUTHORIZED.getMsg()));
    }

    /**
     * 获得用户分类列表信息
     * @return
     */
    @GetMapping("/anno/typelist")
    @ApiOperation("获得用户分类列表信息")
    public R<List<TypeDTO>> getTypes() {
        List<Type> typeList = typeService.list();
        List<TypeDTO> dtoList = dozerUtils.mapList(typeList, TypeDTO.class);
        return success(dtoList);
    }

    /**
     * 新增分类信息
     * @param dto
     * @return
     */
    @PostMapping
    @ApiOperation("新增分类信息")
    public R<Boolean> addType(@Validated @RequestBody TypeAddDTO dto) {
        if (getAdmin()) {
            if (typeService.isTypeExist(dto.getName())) {
                return fail("分类已存在");
            }
            Type type = Type.builder()
                    .name(dto.getName())
                    .build();
            typeService.save(type);
            return success();
        }
        // 权限不足
        return fail(new BizException(ExceptionCode.UNAUTHORIZED.getCode(), ExceptionCode.UNAUTHORIZED.getMsg()));
    }

    /**
     * 修改分类信息
     * @param dto
     * @return
     */
    @PutMapping
    @ApiOperation("修改分类信息")
    public R<Boolean> updateType(@Validated @RequestBody TypeUpdateDTO dto) {
        if (getAdmin()) {
            LambdaUpdateWrapper<Type> lbu = new LambdaUpdateWrapper<>();
            lbu.eq(Type::getTypeId, dto.getTypeId())
                    .set(Type::getName, dto.getName());
            typeService.update(lbu);
            log.info("修改分类[%s]成功，参数信息: %s", dto.getTypeId(), dto);
            return success();
        }
        // 权限不足
        return fail(new BizException(ExceptionCode.UNAUTHORIZED.getCode(), ExceptionCode.UNAUTHORIZED.getMsg()));
    }


    /**
     * 删除分类信息
     * @param typeId
     * @return
     */
    @DeleteMapping("/{typeId}")
    @ApiOperation("删除用户分类信息")
    public R<Boolean> deleteType(@PathVariable("typeId") Long typeId) {
        if (typeId==null) {
            return fail("缺少分类ID参数");
        }
        // 管理员鉴权
        if (getAdmin()) {
            typeService.removeType(typeId);
            return success();
        }
        return fail(new BizException(ExceptionCode.UNAUTHORIZED.getCode(), ExceptionCode.UNAUTHORIZED.getMsg()));
    }
}
