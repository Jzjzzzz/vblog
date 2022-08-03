package com.jzj.vblog.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jzj.vblog.web.mapper.ArticleInformMapper;
import com.jzj.vblog.web.pojo.vo.ArticleVo;
import com.jzj.vblog.web.service.ArticleInformService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    public void test01(){
        Page<ArticleVo> articleVoPage = articleInformMapper.selectPageVo(new Page<>(1, 1));
        List<ArticleVo> records = articleVoPage.getRecords();
        System.out.println(records);
    }

    @Test
    public void test02(){
    }
}
