package com.kemorebi.forum.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageInfo;
import com.kemorebi.forum.common.BaseController;
import com.kemorebi.forum.common.R;
import com.kemorebi.forum.exception.BizException;
import com.kemorebi.forum.exception.code.ExceptionCode;
import com.kemorebi.forum.model.dto.PageDTO;
import com.kemorebi.forum.model.entity.Article;
import com.kemorebi.forum.model.entity.Comment;
import com.kemorebi.forum.service.ArticleService;
import com.kemorebi.forum.service.CommentService;
import com.kemorebi.forum.utils.DozerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Api(tags = "管理员控制器", value = "管理员操作")
@RestController
@RequestMapping("admin")
public class AdminController extends BaseController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;

    /**
     * 获得所有的评论分页信息
     * @param pageNum
     * @param pageSize
     * @param status 审核状态，null表示无影响
     * @return
     */
    @GetMapping("/comment")
    @ApiOperation("获得所有的评论分页信息")
    public R<PageDTO> getCommentList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", required = false, defaultValue = "20")  int pageSize,
                                     @RequestParam(value = "status", required = false) Boolean status) {
        if (getAdmin()) {
            PageInfo pageInfo = commentService.getCommentPage(status, pageNum, pageSize);
            PageDTO pageDTO = new PageDTO(pageInfo);
            return success(pageDTO);
        }
        return fail(new BizException(ExceptionCode.UNAUTHORIZED.getCode(), ExceptionCode.UNAUTHORIZED.getMsg()));
    }

    /**
     * 提交评论审核状态
     * @param comId
     * @param status
     * @return
     */
    @PostMapping("/comment")
    @ApiOperation("提交评论审核状态")
    public R<Boolean> examCommentStatus(@RequestParam(value = "comId") Long comId,
                                 @RequestParam(value = "status") Boolean status) {
        if (getAdmin()) {
            LambdaUpdateWrapper<Comment> lbq = new LambdaUpdateWrapper<>();
            lbq.eq(Comment::getComId, comId)
                    .set(Comment::getStatus, status);
            commentService.update(lbq);
            log.info("评论[%s]审核状态修改为[%s]", comId, status);
            return success();
        }
        return fail(new BizException(ExceptionCode.UNAUTHORIZED.getCode(), ExceptionCode.UNAUTHORIZED.getMsg()));
    }


    /**
     * 获得所有的文章分页信息
     * @param pageNum
     * @param pageSize
     * @param status 审核状态，null表示无影响
     * @return
     */
    @GetMapping("/article")
    @ApiOperation("获得所有的文章分页信息")
    public R<PageDTO> getArticleList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", required = false, defaultValue = "20")  int pageSize,
                                     @RequestParam(value = "status", required = false) Boolean status) {
        if (getAdmin()) {
            PageInfo pageInfo = articleService.getArticlePage(status, pageNum, pageSize);
            PageDTO pageDTO = new PageDTO(pageInfo);
            return success(pageDTO);
        }
        return fail(new BizException(ExceptionCode.UNAUTHORIZED.getCode(), ExceptionCode.UNAUTHORIZED.getMsg()));
    }

    /**
     * 提交文章审核状态
     * @param aid
     * @param status
     * @return
     */
    @PostMapping("/article")
    @ApiOperation("提交文章审核状态")
    public R<Boolean> examArticleStatus(@RequestParam(value = "aid") Long aid,
                                 @RequestParam(value = "status") Boolean status) {
        if (getAdmin()) {
            LambdaUpdateWrapper<Article> lbq = new LambdaUpdateWrapper<>();
            lbq.eq(Article::getAid, aid)
                    .set(Article::getStatus, status);
            articleService.update(lbq);
            log.info("文章[%s]审核状态修改为[%s]", aid, status);
            return success();
        }
        return fail(new BizException(ExceptionCode.UNAUTHORIZED.getCode(), ExceptionCode.UNAUTHORIZED.getMsg()));
    }

    /**
     * 根据用户ID获得所有评论
     * @param pageNum
     * @param pageSize
     * @param uid
     * @return
     */
    @GetMapping("/userComment")
    @ApiOperation("根据用户ID获得所有评论")
    public R<PageDTO> getUserComment(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", required = false, defaultValue = "20")  int pageSize,
                                     @RequestParam(value = "uid", required = false) Long uid,
                                     @RequestParam(value = "status", required = false) Boolean status) {
        if (getAdmin()) {
            PageInfo pageInfo = commentService.getUserComment(uid, pageNum, pageSize, status);
            PageDTO pageDTO = new PageDTO(pageInfo);
            return success(pageDTO);
        }
        return fail(new BizException(ExceptionCode.UNAUTHORIZED.getCode(), ExceptionCode.UNAUTHORIZED.getMsg()));

    }
}

