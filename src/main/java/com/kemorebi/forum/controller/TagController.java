package com.kemorebi.forum.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageInfo;
import com.kemorebi.forum.common.BaseController;
import com.kemorebi.forum.common.R;
import com.kemorebi.forum.model.dto.PageDTO;
import com.kemorebi.forum.model.dto.TagAddDTO;
import com.kemorebi.forum.model.dto.TagDTO;
import com.kemorebi.forum.model.dto.TagUpdateDTO;
import com.kemorebi.forum.model.entity.Tag;
import com.kemorebi.forum.service.TagService;
import com.kemorebi.forum.utils.DozerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 葵gui
 * @since 2023-05-18
 */
@Slf4j
@Api(tags = "标签控制器", value = "标签对应的操作")
@RestController
@RequestMapping("/tag")
public class TagController extends BaseController {

    @Autowired
    private TagService tagService;

    @Autowired
    private DozerUtils dozerUtils;

    /**
     * 获得用户标签信息
     * @param tagId
     * @return
     */
    @GetMapping("/{tagId}")
    @ApiOperation("获得用户标签信息")
    public R<TagDTO> getTag(@PathVariable("tagId") Long tagId) {
        Long uid = getUserId();
        LambdaQueryWrapper<Tag> lbq = new LambdaQueryWrapper<>();
        lbq.eq(Tag::getUid, uid)
                .eq(Tag::getTagId, tagId);
        Tag tag = tagService.getOne(lbq);
        TagDTO dto = dozerUtils.map(tag, TagDTO.class);
        return success(dto);
    }

    /**
     * 获得用户标签列表信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/tagList")
    @ApiOperation("获得用户标签列表信息")
    public R<PageDTO> getTags(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "20")  int pageSize) {
        Long uid = getUserId();
        PageInfo pageInfo = tagService.getTagListByUid(uid, pageNum, pageSize);
        PageDTO pageDTO = new PageDTO(pageInfo);
        return success(pageDTO);
    }

    /**
     * 添加用户标签
     * @return
     */
    @PostMapping
    @ApiOperation("添加用户标签")
    public R<Boolean> addTag(@Validated @RequestBody TagAddDTO dto) {
        Long uid = getUserId();
        if (tagService.isTagExist(uid, dto.getName())) {
            return fail("标签已存在");
        }
        if (tagService.isTagMax(uid)) {
            return fail("用户标签数量已达上限");
        }
        tagService.saveTag(dto, uid);
        return success();
    }

    /**
     * 修改用户标签
     * @return
     */
    @PutMapping
    @ApiOperation("修改用户标签")
    public R<Boolean> updateTag(@Validated @RequestBody TagUpdateDTO dto) {
        Long uid = getUserId();
        LambdaUpdateWrapper<Tag> lbu = new LambdaUpdateWrapper<>();
        lbu.eq(Tag::getTagId, dto.getTagId())
                .eq(Tag::getUid, uid)
                .set(Tag::getName, dto.getName());
        tagService.update(lbu);
        log.info("修改标签[%s]成功, 参数信息:%s", dto.getTagId(), dto);
        return success();
    }

    /**
     * 删除用户标签
     * @param tagId
     * @return
     */
    @DeleteMapping("/{tagId}")
    @ApiOperation("删除用户标签")
    public R<Boolean> deleteTag(@PathVariable("tagId") Long tagId) {
        if (tagId==null) {
            return fail("确实标签ID参数");
        }
        Long uid = getUserId();
        tagService.removeTag(tagId, uid);
        return success();
    }

}
