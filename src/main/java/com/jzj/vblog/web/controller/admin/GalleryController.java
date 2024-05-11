package com.jzj.vblog.web.controller.admin;


import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.Gallery;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.service.GalleryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 图集 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-09-24
 */
@RestController
@RequestMapping("/gallery")
public class GalleryController extends BaseController {
    @Autowired
    private GalleryService galleryService;

    @ApiOperation("分页列表")
    @GetMapping
    @PreAuthorize("hasAuthority('btn.gallery.list')")
    public TableDataInfo pageList(Gallery gallery) {
        startPage();
        List<Gallery> list = galleryService.selectGalleryList(gallery);
        return getDataTable(list);
    }

    @Log(title = "图片管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增图片")
    @PostMapping
    @PreAuthorize("hasAuthority('btn.gallery.add')")
    public R add(@Validated @RequestBody Gallery gallery) {
        gallery.setStatus("1");
        return toAjax(galleryService.save(gallery));
    }

    @Log(title = "图片管理", businessType = BusinessType.DELETE)
    @ApiOperation("删除图片")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('btn.gallery.del')")
    public R del(@PathVariable String id, HttpServletRequest request) {
        return toAjax(galleryService.removeByIdImg(id, request));
    }
}

