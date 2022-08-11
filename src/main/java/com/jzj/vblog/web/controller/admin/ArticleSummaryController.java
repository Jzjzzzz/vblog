package com.jzj.vblog.web.controller.admin;


import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.ArticleSummary;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.service.ArticleSummaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2022-08-11
 */
@RestController
@RequestMapping("/article/summary")
public class ArticleSummaryController extends BaseController {

    @Autowired
    private ArticleSummaryService articleSummaryService;

    @ApiOperation("分页列表")
    @GetMapping("/list")
    public TableDataInfo list(ArticleSummary articleSummary)
    {
        startPage();
        List<ArticleSummary> list = articleSummaryService.selectSummaryList(articleSummary);
        return getDataTable(list);
    }

    @Log(title = "归档管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增归档")
    @PostMapping("/add")
    public R add(@Validated @RequestBody ArticleSummary articleSummary)
    {
        if (UserConstants.NOT_UNIQUE.equals(articleSummaryService.checkSummaryUnique(articleSummary)))
        {
            return R.error("新增归档'" + articleSummary.getName() + "'失败，归档已存在");
        }
        return toAjax(articleSummaryService.insertSummary(articleSummary));
    }

    /**
     * 删除归档
     */
    @Log(title = "归档管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable List<String> ids, HttpServletRequest request)
    {
        return toAjax(articleSummaryService.deleteSummaryByIds(ids,request));
    }

}

