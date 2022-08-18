package com.jzj.vblog.web.controller.admin;


import cn.dev33.satoken.annotation.SaCheckLogin;
import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.WebsiteResource;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.service.WebsiteResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@SaCheckLogin
public class WebsiteResourceController extends BaseController {
    @Autowired
    private WebsiteResourceService websiteResourceService;

    @ApiOperation("分页列表")
    @GetMapping("/list")
    public TableDataInfo list(WebsiteResource websiteResource) {
        startPage();
        List<WebsiteResource> list = websiteResourceService.selectWebsiteList(websiteResource);
        return getDataTable(list);
    }

    @Log(title = "资源站点", businessType = BusinessType.INSERT)
    @ApiOperation("新增资源")
    @PostMapping("/add")
    public R add(@Validated @RequestBody WebsiteResource websiteResource) {
        if (UserConstants.NOT_UNIQUE.equals(websiteResourceService.checkWebsiteUnique(websiteResource))) {
            return R.error("新增资源'" + websiteResource.getResourceName() + "'失败，资源已存在");
        }
        return toAjax(websiteResourceService.insertWebsite(websiteResource));
    }

    /**
     * 根据资源id获取资源详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable String id) {
        return R.ok(websiteResourceService.selectWebsiteById(id));
    }

    /**
     * 修改资源
     */
    @Log(title = "资源站点", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@Validated @RequestBody WebsiteResource websiteResource) {
        if (UserConstants.NOT_UNIQUE.equals(websiteResourceService.checkWebsiteUnique(websiteResource))) {
            return R.error("修改资源'" + websiteResource.getResourceName() + "'失败，资源已存在");
        }
        return toAjax(websiteResourceService.updateWebsite(websiteResource));
    }

    /**
     * 删除资源
     */
    @Log(title = "资源站点", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable List<String> ids, HttpServletRequest request) {
        return toAjax(websiteResourceService.deleteWebsiteByIds(ids, request));
    }

}

