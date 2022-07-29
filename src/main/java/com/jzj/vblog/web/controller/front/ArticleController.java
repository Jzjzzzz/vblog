package com.jzj.vblog.web.controller.front;

import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.vo.ArticleVo;
import com.jzj.vblog.web.service.ArticleContentService;
import com.jzj.vblog.web.service.ArticleInformService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author Jzj
 * @Date 2022/7/29 17:17
 * @Version 1.0
 * @Message: 前台文章控制器
 */
@Controller
public class ArticleController extends BaseController {

    @Autowired
    private ArticleInformService articleInformService;

    @Autowired
    private ArticleContentService articleContentService;

    @ApiOperation("获取文章列表")
    @GetMapping("/listPage/{page}/{limit}")
    public R listPage(@PathVariable Long page, @PathVariable Long limit){
        List<ArticleVo> list = articleInformService.listPage(page,limit);
        return R.ok("list",list);
    }
}
