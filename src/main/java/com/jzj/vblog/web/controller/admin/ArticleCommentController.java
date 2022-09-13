package com.jzj.vblog.web.controller.admin;


import cn.dev33.satoken.annotation.SaCheckLogin;
import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.ArticleComment;
import com.jzj.vblog.web.pojo.entity.FriendLink;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.pojo.vo.CommentInfoVo;
import com.jzj.vblog.web.service.ArticleCommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 留言板 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-09-11
 */
@RestController
@RequestMapping("/article/comment")
@SaCheckLogin
public class ArticleCommentController extends BaseController {

    @Autowired
    private ArticleCommentService articleCommentService;

    @ApiOperation("分页列表")
    @GetMapping("/pageList")
    public TableDataInfo pageList(ArticleComment articleComment) {
        startPage();
        List<ArticleComment> list = articleCommentService.selectCommentList(articleComment);
        return getDataTable(list);
    }

    @Log(title = "评论管理", businessType = BusinessType.INSERT)
    @ApiOperation("回复评论")
    @PostMapping("/reply")
    public R reply(@Validated @RequestBody CommentInfoVo commentInfoVo) {
        return toAjax(articleCommentService.reply(commentInfoVo));
    }

    @ApiOperation("根据id获取详情")
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable String id) {
        return R.ok(articleCommentService.selectCommentById(id));
    }


    @Log(title = "评论管理", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改回复")
    public R edit(@Validated @RequestBody CommentInfoVo commentInfoVo) {
        return toAjax(articleCommentService.updateComment(commentInfoVo));
    }

    @Log(title = "评论管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除评论")
    public R remove(@PathVariable List<String> ids) {
        return toAjax(articleCommentService.deleteCommentByIds(ids));
    }
}

