package com.jzj.vblog.web.controller.front;

import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.ArticleComment;
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

    @ApiOperation("前台评论留言板")
    @PostMapping("/insert/message")
    public R saveMessage(@RequestBody ArticleComment articleComment){
        return toAjax(articleCommentService.saveUserMessage(articleComment));
    }

    @ApiOperation("前台显示留言板列表")
    @GetMapping("/getList/message/{pageNumber}")
    public R getMessageList(@PathVariable Integer pageNumber){
        Map<String,Object> map = articleCommentService.getMessageList(pageNumber);
        return R.ok(map);
    }
}
