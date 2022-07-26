package com.jzj.vblog.web.controller.admin;


import com.jzj.vblog.web.pojo.entity.WebsiteResource;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.service.WebsiteResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 资源站点 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-07-26
 */
@Api(tags = "资源站点管理")
@RestController
@RequestMapping("/website/resource")
public class WebsiteResourceController extends BaseController{
    @Autowired
    private WebsiteResourceService websiteResourceService;

    @ApiOperation("分页列表")
    @GetMapping("/list")
    public TableDataInfo list(WebsiteResource websiteResource)
    {
        startPage();
        List<WebsiteResource> list = websiteResourceService.selectWebsiteList(websiteResource);
        return getDataTable(list);
    }
}

