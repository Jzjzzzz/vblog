package com.jzj.vblog.web.controller.admin;

import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.utils.result.ResponseEnum;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.ArticleSummary;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.service.ArticleSummaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 文章归档 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022/7/22 11:12
 */
@Api(tags = "文章归档管理")
@RestController
@RequestMapping("/article/summary")
public class ArticleSummaryController extends BaseController {

    @Autowired
    private ArticleSummaryService articleSummaryService;

    @ApiOperation("分页列表")
    @GetMapping
    @PreAuthorize("hasAuthority('btn.summary.list')")
    public TableDataInfo list(ArticleSummary articleSummary) {
        startPage();
        List<ArticleSummary> list = articleSummaryService.selectSummaryList(articleSummary);
        return getDataTable(list);
    }

    @ApiOperation("查询归档列表")
    @GetMapping("/listAll")
    @PreAuthorize("hasAuthority('btn.summary.list')")
    public R listAll() {
        return R.ok(articleSummaryService.list());
    }

    @Log(title = "归档管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增归档")
    @PostMapping
    @PreAuthorize("hasAuthority('btn.summary.add')")
    public R add(@Validated @RequestBody ArticleSummary articleSummary) {
        if (UserConstants.NOT_UNIQUE.equals(articleSummaryService.checkSummaryUnique(articleSummary))) {
            throw new BusinessException("新增归档'" + articleSummary.getName() + "'失败，归档已存在");
        }
        if (articleSummaryService.checkSummaryTop(articleSummary)) {
            throw new BusinessException(ResponseEnum.SUMMARY_TOP_NUMBER_MAX);
        }
        return toAjax(articleSummaryService.insertSummary(articleSummary));
    }

    @ApiOperation("根据资源id获取归档详细信息")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('btn.summary.list')")
    public R getInfo(@PathVariable String id) {
        return R.ok(articleSummaryService.selectSummaryById(id));
    }

    @ApiOperation("修改归档")
    @Log(title = "归档管理", businessType = BusinessType.UPDATE)
    @PutMapping
    @PreAuthorize("hasAuthority('btn.summary.edit')")
    public R edit(@Validated @RequestBody ArticleSummary articleSummary) {
        if (UserConstants.NOT_UNIQUE.equals(articleSummaryService.checkSummaryUnique(articleSummary))) {
            throw new BusinessException("修改归档'" + articleSummary.getName() + "'失败，归档已存在");
        }
        if (articleSummaryService.checkSummaryTop(articleSummary)) {
            throw new BusinessException(ResponseEnum.SUMMARY_TOP_NUMBER_MAX);
        }
        return toAjax(articleSummaryService.updateSummary(articleSummary));
    }

    @ApiOperation("删除归档")
    @Log(title = "归档管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAuthority('btn.summary.del')")
    public R remove(@PathVariable List<String> ids, HttpServletRequest request) {
        return toAjax(articleSummaryService.deleteSummaryByIds(ids, request));
    }

    @ApiOperation("根据归档id查询文章列表")
    @GetMapping("/articleList/{id}")
    @PreAuthorize("hasAuthority('btn.article.list')")
    public R articleList(@PathVariable String id) {
        return R.ok(articleSummaryService.articleList(id));
    }

}

