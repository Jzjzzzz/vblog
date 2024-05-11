package com.jzj.vblog.web.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.pojo.entity.FriendLink;
import com.jzj.vblog.web.service.FriendLinkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/front/link")
public class LinkController {
    @Autowired
    private FriendLinkService friendLinkService;

    @ApiOperation("友链列表")
    @GetMapping("/list")
    public R list(){
        return R.ok(friendLinkService.list(new QueryWrapper<FriendLink>().eq("status","1")));
    }
}
