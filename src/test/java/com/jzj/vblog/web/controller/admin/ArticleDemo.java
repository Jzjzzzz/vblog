package com.jzj.vblog.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jzj.vblog.web.mapper.ArticleCommentMapper;
import com.jzj.vblog.web.mapper.ArticleInformMapper;
import com.jzj.vblog.web.pojo.entity.ArticleComment;
import com.jzj.vblog.web.service.ArticleInformService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author Jzj
 * @Date 2022/8/2 15:37
 * @Version 1.0
 * @Message:
 */
@SpringBootTest
public class ArticleDemo {
    @Autowired
    ArticleInformMapper articleInformMapper;

    @Autowired
    ArticleInformService articleInformService;

    @Autowired
    ArticleCommentMapper articleCommentMapper;

    @Test
    public void test01(){
        Page<ArticleComment> commentPage = new Page<>(1, 1);
        Page<ArticleComment> page = articleCommentMapper.selectPage(commentPage, null);
        System.out.println(page.getRecords());
    }

}
