package com.jzj.vblog.web.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.pojo.entity.FriendLink;
import com.jzj.vblog.web.service.FriendLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 友联前台控制器
 * </p>
 *
 * @author Jzj
 * @since 2022/7/22 11:12
 */
@Api(tags = "友联前台控制器")
@RestController
@RequestMapping("/front/link")
public class LinkController {
    @Autowired
    private FriendLinkService friendLinkService;

    @ApiOperation("友链列表")
    @GetMapping("/list")
    public R list() {
        return R.ok(friendLinkService.list(new QueryWrapper<FriendLink>().eq("status", "1")));
    }
}
