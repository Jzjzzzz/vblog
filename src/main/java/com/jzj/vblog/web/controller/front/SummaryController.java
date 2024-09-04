package com.jzj.vblog.web.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.pojo.entity.ArticleSummary;
import com.jzj.vblog.web.service.ArticleSummaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 归档前台控制器
 * </p>
 *
 * @author Jzj
 * @since 2022/7/22 11:12
 */
@Api(tags = "归档前台控制器")
@RestController
@Slf4j
@RequestMapping("/front/summary")
public class SummaryController {
    @Autowired
    private ArticleSummaryService articleSummaryService;


    @ApiOperation("归档列表")
    @GetMapping("/list")
    public R list(){
        return R.ok(articleSummaryService.list(new QueryWrapper<ArticleSummary>().eq("status","1")));
    }
}
