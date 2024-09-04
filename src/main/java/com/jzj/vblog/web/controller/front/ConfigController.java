package com.jzj.vblog.web.controller.front;

import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.service.SysWebInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 全局配置相关控制器
 * </p>
 *
 * @author Jzj
 * @since 2022/7/22 11:12
 */
@Api(tags = "全局配置相关控制器")
@RestController
@RequestMapping("/front/config")
public class ConfigController {

    @Autowired
    private SysWebInformationService sysWebInformationService;

    @ApiOperation("获取网站基础信息")
    @GetMapping(value = "/getConfig")
    public R getInfo() {
        return R.ok(sysWebInformationService.selectWebInformationById());
    }
}
