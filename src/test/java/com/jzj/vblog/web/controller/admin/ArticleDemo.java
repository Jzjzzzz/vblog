package com.jzj.vblog.web.controller.admin;

import com.jzj.vblog.web.mapper.ArticleInformMapper;
import com.jzj.vblog.web.service.ArticleInformService;
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

}
