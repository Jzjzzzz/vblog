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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @GetMapping
    @PreAuthorize("hasAuthority('btn.article.list')")
    public TableDataInfo list(ArticleInform entity) {
        startPage();
        List<ArticleInform> list = articleInformService.selectList(entity);
        return getDataTable(list);
    }

    @Log(title = "文章管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增文章")
    @PostMapping
    @PreAuthorize("hasAuthority('btn.article.add')")
    public R add(@RequestBody ArticleAddVo vo) {
        return toAjax(articleInformService.articleAdd(vo));
    }

    @ApiOperation("根据Id获取文章")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('btn.article.list')")
    public R getById(@PathVariable String id) {
        return R.ok(articleInformService.getArticleById(id));
    }

    @Log(title = "文章管理", businessType = BusinessType.UPDATE)
    @ApiOperation("根据Id修改文章")
    @PutMapping
    @PreAuthorize("hasAuthority('btn.article.edit')")
    public R updateById(@RequestBody ArticleAddVo vo) {
        return toAjax(articleInformService.updateArticleById(vo));
    }

    @Log(title = "文章管理", businessType = BusinessType.DELETE)
    @ApiOperation("根据Id批量删除文章")
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAuthority('btn.article.del')")
    public R deleteBthById(@PathVariable String[] ids, HttpServletRequest request) {
        return toAjax(articleInformService.deleteArticleById(ids, request));
    }

    @Log(title = "文章管理", businessType = BusinessType.UPDATE)
    @ApiOperation("根据id修改文章归档")
    @PutMapping("updateSummaryById/{id}/{summaryId}/{type}")
    @PreAuthorize("hasAuthority('btn.article.edit')")
    public R updateSummaryById(@PathVariable String id, @PathVariable String summaryId, @PathVariable String type) {
        return toAjax(articleInformService.updateSummaryById(id, summaryId, type));
    }

    @ApiOperation("获取文章后台排行榜")
    @GetMapping("/getRank")
    public R getRank() {
        return R.ok(articleInformService.getRank());
    }
}

