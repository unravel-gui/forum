package com.kemorebi.forum.controller;

import com.github.pagehelper.PageInfo;
import com.kemorebi.forum.common.BaseController;
import com.kemorebi.forum.common.R;
import com.kemorebi.forum.exception.BizException;
import com.kemorebi.forum.exception.code.ExceptionCode;
import com.kemorebi.forum.model.dto.*;
import com.kemorebi.forum.model.entity.Article;
import com.kemorebi.forum.service.ArticleService;
import com.kemorebi.forum.service.ArticleUserService;
import com.kemorebi.forum.utils.DozerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@Api(tags = "文章控制器", value = "提供文章操作")
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleUserService articleUserService;

    /**
     * 用户本人或管理员获得全部文章信息(无论是否通过审核)
     * @param aid
     * @return
     */
    @GetMapping("/{aid}")
    public R<ArticleDTO> getArticle(@PathVariable("aid") Long aid) {
        if (aid==null) {
            return fail("缺少文章ID参数");
        }
        Long uid = getUserId();
        Long author = articleUserService.getUidByArticleId(aid);
        // 只有作者和管理员可以获得文章信息
        if (uid==author||getAdmin()) {
            ArticleDTO dto = articleService.getArticleByAid(aid, null);
            return success(dto);
        }
        // 未授权
        return fail(new BizException(ExceptionCode.UNAUTHORIZED.getCode(), ExceptionCode.UNAUTHORIZED.getMsg()));
    }

    /**
     * 获得用户文章列表
     * @return
     */
    @GetMapping
    @ApiOperation("获得用户文章列表")
    public R<PageDTO> getUserArticleList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "20")  int pageSize) {
        Long uid = getUserId();
        PageInfo<ArticleSimDTO> pageInfo = articleService.getArticleListByUid(uid, pageNum, pageSize);
        PageDTO pageDTO = new PageDTO(pageInfo);
        return success(pageDTO);
    }

    /**
     * 用户提交文章
     * @param dto
     * @return
     */
    @PostMapping
    @ApiOperation("新增文章")
    @ApiImplicitParam(name = "dto", dataType = "ArticleAddDTO")
    public R<Boolean> addArticle(@Validated @RequestBody ArticleAddDTO dto) {
        Long uid = getUserId();
        // 初始化文章信息
        articleService.saveArticle(dto, uid);
        return success();
    }

    /**
     * 修改文章, 只允许本人修改
     * @param dto
     * @return
     */
    @PutMapping
    @ApiOperation("修改文章")
    public R<Boolean> updateArticle(@Validated @RequestBody ArticleUpdateDTO dto) {
        Long uid = getUserId();
        Long author = articleUserService.getUidByArticleId(dto.getAid());
        if (author==uid) {
            articleService.updateArticle(dto);
            return success();
        }
        // 未授权操作
        return fail(new BizException(ExceptionCode.UNAUTHORIZED.getCode(), ExceptionCode.UNAUTHORIZED.getMsg()));
    }

    /**
     * 删除文章只允许本人删除
     * @param aid
     * @return
     */
    @DeleteMapping("/{aid}")
    public R<Boolean> deleteArticle(@PathVariable("aid") Long aid) {
        if (aid==null) {
            return fail("缺少文章ID参数");
        }
        Long uid = getUserId();
        Long author = articleUserService.getUidByArticleId(aid);
        if (uid==author) {
            articleService.removeArticle(aid);
            return success();
        }
        // 未授权操作
        return fail(new BizException(ExceptionCode.UNAUTHORIZED.getCode(), ExceptionCode.UNAUTHORIZED.getMsg()));
    }


    /**
     * 点赞
     * 没对用户点赞某文章做持久化，所以同一个用户可以重复点赞同一篇文章
     * @param aid
     * @return
     */
    @GetMapping("/likeit/{aid}")
    public R<Boolean> query(@PathVariable("aid") Long aid){
        articleService.addLikeCount(aid);
        return success();
    }

}
