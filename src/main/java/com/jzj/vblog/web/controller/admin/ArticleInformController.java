package com.jzj.vblog.web.controller.admin;


import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.ArticleInform;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.pojo.vo.ArticleAddVo;
import com.jzj.vblog.web.service.ArticleInformService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class ArticleInformController extends BaseController {

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

    @Log(title = "文章管理",businessType = BusinessType.INSERT)
    @ApiOperation("新增文章")
    @PostMapping("/add")
    public R add(@RequestBody ArticleAddVo vo){
        return toAjax(articleInformService.articleAdd(vo));
    }

    @ApiOperation("根据Id获取文章")
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable String id){
        ArticleAddVo articleAddVo = articleInformService.getArticleById(id);
        return R.ok(articleAddVo);
    }
}

