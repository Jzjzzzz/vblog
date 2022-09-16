package com.jzj.vblog.web.controller.front;

import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.ArticleComment;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.service.ArticleCommentService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 评论控制器
 */
@RestController
@RequestMapping("/front/comment")
@Slf4j
public class CommentController extends BaseController {
    @Autowired
    private ArticleCommentService articleCommentService;

    @ApiOperation("前台评论")
    @PostMapping("/insert/message")
    @Log(title = "前台评论", businessType = BusinessType.INSERT)
    public R saveMessage(@RequestBody ArticleComment articleComment){
        if(!articleCommentService.checkFrontData(articleComment)){
            return R.error("数据异常!");
        }
        return toAjax(articleCommentService.saveUserMessage(articleComment));
    }

    @ApiOperation("前台显示留言板列表")
    @GetMapping("/getList/message/{pageNumber}")
    public R getMessageList(@PathVariable Integer pageNumber){
        Map<String,Object> map = articleCommentService.getMessageList(pageNumber);
        return R.ok(map);
    }

    @ApiOperation("前台显示文章评论列表")
    @GetMapping("/getList/article/{articleId}")
    public R getListArticle(@PathVariable String articleId){
        Map<String,Object> map = articleCommentService.getListArticle(articleId);
        return R.ok(map);
    }

}
