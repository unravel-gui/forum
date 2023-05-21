package com.kemorebi.forum.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.kemorebi.forum.common.BaseController;
import com.kemorebi.forum.common.R;
import com.kemorebi.forum.exception.BizException;
import com.kemorebi.forum.exception.code.ExceptionCode;
import com.kemorebi.forum.model.dto.CommentAddDTO;
import com.kemorebi.forum.model.dto.CommentDTO;
import com.kemorebi.forum.model.dto.PageDTO;
import com.kemorebi.forum.service.CommentService;
import io.swagger.annotations.Api;
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
@Api(tags = "评论控制器", value = "提供评论操作")
@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    /**
     * 获得文章的已过审评论
     * @param aid
     * @return
     */
    @GetMapping("/anno/{aid}")
    @ApiOperation("获得文章的已过审评论")
    public R<List<CommentDTO>> getArticleCommonts(@PathVariable("aid") Long aid) {
        List<CommentDTO> commontDTOS =  commentService.getCommentByAid(aid, true);
        return success(commontDTOS);
    }

    /**
     * 添加文章评论
     * @param dto
     * @return
     */
    @PostMapping
    @ApiOperation("添加文章评论")
    public R<Boolean> addComment(@Validated @RequestBody CommentAddDTO dto) {
        Long uid = getUserId();
        commentService.addComment(dto, uid);
        return success();
    }

    /**
     * 删除文章评论
     * @param comId
     * @return
     */
    @DeleteMapping("/{comId}")
    @ApiOperation("删除文章评论")
    public R<Boolean> deleteComment(@PathVariable("comId") Long comId) {
        if (comId==null) {
            return fail("缺少评论ID参数");
        }
        // 判断能否删除
        Boolean flag = commentService.canDeleteComment(comId, getUserId(), getAdmin());
        if (flag){
            commentService.deleteComment(comId);
            return success();
        }
        return fail(new BizException(ExceptionCode.UNAUTHORIZED.getCode(), ExceptionCode.UNAUTHORIZED.getMsg()));
    }

    /**
     *  获得用户的所有评论
     * @return
     */
    @GetMapping("userComment")
    public R<PageDTO> getUserComment(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "20")  int pageSize) {
        Long uid = getUserId();
        PageInfo pageInfo = commentService.getUserComment(uid, pageNum, pageSize);
        PageDTO pageDTO = new PageDTO(pageInfo);
        return success(pageDTO);
    }

    /**
     *  获得用户文章的所有评论
     * @return
     */
    @GetMapping("author")
    public R<PageDTO> getComment(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "20")  int pageSize) {
        Long uid = getUserId();
        PageInfo pageInfo = commentService.getArticleComment(uid, pageNum, pageSize);
        PageDTO pageDTO = new PageDTO(pageInfo);
        return success(pageDTO);
    }
}
