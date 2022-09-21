package com.jzj.vblog.web.controller.admin;


import cn.dev33.satoken.annotation.SaCheckLogin;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.service.SysCountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 统计归档 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-08-23
 */
@RestController
@RequestMapping("/sys/count")
@SaCheckLogin
public class SysCountController {

    @Autowired
    private SysCountService sysCountService;

    @ApiOperation("获取统计数据")
    @GetMapping
    public R getCount() {
        return R.ok(sysCountService.getOne(null));
    }
}
