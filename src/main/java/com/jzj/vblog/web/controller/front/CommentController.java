package com.jzj.vblog.web.controller.front;

import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.ArticleComment;
import com.jzj.vblog.web.service.ArticleCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评论控制器
 */
@RestController
@RequestMapping("/front/comment")
@Slf4j
public class CommentController extends BaseController {
    @Autowired
    private ArticleCommentService articleCommentService;

    @PostMapping("/insert/message")
    public R saveMessage(@RequestBody ArticleComment articleComment){
        return toAjax(articleCommentService.saveUserMessage(articleComment));
    }
}
