package com.jzj.vblog.web.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.ArticleSummary;
import com.jzj.vblog.web.pojo.vo.ArticleAddVo;
import com.jzj.vblog.web.service.ArticleInformService;
import com.jzj.vblog.web.service.ArticleSummaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Jzj
 * @Date 2022/7/29 17:17
 * @Version 1.0
 * @Message: 前台文章控制器
 */
@RestController
@RequestMapping("/front/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleInformService articleInformService;

    @Autowired
    private ArticleSummaryService articleSummaryService;

    @ApiOperation("分页获取文章列表")
    @PostMapping("/listPage")
    public R listPage(@RequestBody Map<String, Object> queryMap) {
        HashMap<String, Object> map = articleInformService.listPage(queryMap);
        return R.ok("map", map);
    }

    @ApiOperation("根据Id获取文章")
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable String id) {
        ArticleAddVo articleAddVo = articleInformService.getFrontArticleById(id);
        return R.ok(articleAddVo);
    }

    @ApiOperation("分页获取归档列表")
    @PostMapping("/summaryPage")
    public R summaryPage(@RequestBody Map<String, Object> queryMap) {
        HashMap<String, Object> map = articleSummaryService.summaryPage(queryMap);
        return R.ok("map", map);
    }

    @ApiOperation("获取首页归档列表")
    @GetMapping("/summaryTop")
    public R summaryPage() {
        return R.ok(articleSummaryService.list(new QueryWrapper<ArticleSummary>().eq("top_status", "1").eq("status", "1")));
    }

    @ApiOperation("根据Id点赞文章")
    @GetMapping("/getByIdLike/{id}")
    public R getByIdLike(@PathVariable String id) {
        articleInformService.getByIdLike(id);
        return R.ok("点赞成功");
    }
}
