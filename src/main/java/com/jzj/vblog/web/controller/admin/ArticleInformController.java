package com.jzj.vblog.web.controller.admin;


import com.jzj.vblog.web.pojo.entity.ArticleInform;
import com.jzj.vblog.web.pojo.entity.SysDictType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.service.ArticleInformService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 文章基础信息 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-07-23
 */
@RestController
@RequestMapping("/article/inform")
public class ArticleInformController extends BaseController{

    @Autowired
    private ArticleInformService articleInformService;

    @ApiOperation("分页列表")
    @GetMapping("/list")
    public TableDataInfo list(ArticleInform entity)
    {
        startPage();
        List<ArticleInform> list = articleInformService.selectList(entity);
        return getDataTable(list);
    }
}

