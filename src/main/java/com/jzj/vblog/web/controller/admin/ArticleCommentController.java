package com.jzj.vblog.web.controller.admin;

import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.ArticleComment;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.pojo.vo.CommentInfoVo;
import com.jzj.vblog.web.service.ArticleCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 留言板 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022/7/22 11:12
 */
@Api(tags = "留言板管理")
@RestController
@RequestMapping("/article/comment")
public class ArticleCommentController extends BaseController {

    @Autowired
    private ArticleCommentService articleCommentService;

    @ApiOperation("分页列表")
    @GetMapping
    @PreAuthorize("hasAuthority('btn.comment.list')")
    public TableDataInfo pageList(ArticleComment articleComment) {
        startPage();
        List<ArticleComment> list = articleCommentService.selectCommentList(articleComment);
        return getDataTable(list);
    }

    @Log(title = "评论管理", businessType = BusinessType.INSERT)
    @ApiOperation("回复评论")
    @PostMapping
    @PreAuthorize("hasAuthority('btn.comment.edit')")
    public R reply(@Validated @RequestBody CommentInfoVo commentInfoVo) {
        return toAjax(articleCommentService.reply(commentInfoVo));
    }

    @ApiOperation("根据id获取详情")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('btn.comment.list')")
    public R getInfo(@PathVariable String id) {
        return R.ok(articleCommentService.selectCommentById(id));
    }

    @Log(title = "评论管理", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改回复")
    @PreAuthorize("hasAuthority('btn.comment.edit')")
    public R edit(@Validated @RequestBody CommentInfoVo commentInfoVo) {
        return toAjax(articleCommentService.updateComment(commentInfoVo));
    }

    @Log(title = "评论管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除评论")
    @PreAuthorize("hasAuthority('btn.comment.del')")
    public R remove(@PathVariable String[] ids) {
        return toAjax(articleCommentService.deleteCommentById(ids));
    }

    @Log(title = "评论管理", businessType = BusinessType.UPDATE)
    @PutMapping("/{ids}/{type}")
    @ApiOperation("审核评论")
    @PreAuthorize("hasAuthority('btn.comment.edit')")
    public R audit(@PathVariable String[] ids, @PathVariable String type) {
        return toAjax(articleCommentService.auditCommentById(ids, type));
    }

}

