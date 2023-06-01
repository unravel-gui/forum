package com.kemorebi.forum.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.kemorebi.forum.common.BaseController;
import com.kemorebi.forum.common.R;
import com.kemorebi.forum.model.dto.*;
import com.kemorebi.forum.model.entity.Article;
import com.kemorebi.forum.model.entity.Type;
import com.kemorebi.forum.model.entity.User;
import com.kemorebi.forum.service.ArticleService;
import com.kemorebi.forum.service.TypeService;
import com.kemorebi.forum.service.UserService;
import com.kemorebi.forum.utils.DozerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@Api(tags = "首页控制器", value = "IndexController", description = "无需鉴权")
public class IndexController extends BaseController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private DozerUtils dozerUtils;

    @GetMapping({"/index/hot"})
    @ApiOperation("获得首页数据，根据热度排序")
    public R<IndexDTO> indexHot(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        PageInfo pageInfo = articleService.getIndexPageHot(pageNum, pageSize);
        List<Type> typeList = typeService.list();
        List<TypeDTO> dtoList = dozerUtils.mapList(typeList, TypeDTO.class);
        IndexDTO indexDTO = new IndexDTO(dtoList, pageInfo);
        return success(indexDTO);
    }

    @GetMapping({"/index/time"})
    @ApiOperation("获得首页数据，根据时间排序")
    public R<IndexDTO> indexTime(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        PageInfo pageInfo = articleService.getIndexPageTime(pageNum, pageSize);
        List<Type> typeList = typeService.list();
        List<TypeDTO> dtoList = dozerUtils.mapList(typeList, TypeDTO.class);
        IndexDTO indexDTO = new IndexDTO(dtoList, pageInfo);
        return success(indexDTO);
    }

    /**
     * 任意用户获得已经审核且公开的文章
     * @param aid
     * @return
     */
    @GetMapping("/article/{aid}")
    @ApiOperation("获得已审核的文章信息")
    public R<ArticleDTO> getArticleVisit(@PathVariable("aid") Long aid) {
        if (aid==null) {
            return fail("缺少文章ID参数");
        }
        // 获得已经审核的文章
        ArticleDTO dto = articleService.getArticleByAid(aid,true, true);
        if (dto!=null) {
            // 浏览量加一
            articleService.addPageView(dto.getAid());
        }
        return success(dto);
    }

    /**
     * 获得查询文章分页
     * @param dto
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/query")
    @ApiOperation("获得查询文章分页")
    public R<PageDTO> query(@NotEmpty(message = "查询参数不能为空") @RequestBody QueryParamDTO dto,
                            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                            @RequestParam(value = "pageNum", required = false, defaultValue = "10") int pageSize) {
        // 根据关键字搜索文章
        PageInfo pageInfo = articleService.getQueryArticle(dto, pageNum, pageSize);
        return success(new PageDTO(pageInfo));
    }


    @PostMapping("/queryuser")
    @ApiModelProperty("查询用户")
    public R<List<UserSimDTO>> queryUser(@RequestParam(name = "query") String query) {
        List<UserSimDTO> users = userService.queryUser(query);
        return success(users);
    }
}
