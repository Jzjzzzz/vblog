package com.jzj.vblog.web.controller.admin;


import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.SysTodo;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.service.SysTodoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 备忘录 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-08-14
 */
@RestController
@RequestMapping("/sys/todo")
public class SysTodoController extends BaseController {

    @Autowired
    private SysTodoService sysTodoService;

    @ApiOperation("备忘列表")
    @GetMapping("/list")
    public R list() {
        return R.ok(sysTodoService.list());
    }

    @Log(title = "备忘录", businessType = BusinessType.INSERT)
    @ApiOperation("新增备忘")
    @PostMapping("/add/{content}")
    public R add(@PathVariable String content) {
        if (UserConstants.NOT_UNIQUE.equals(sysTodoService.checkUnique(content))) {
            return R.error("新增事件'" + content + "'失败，事件已存在");
        }
        return toAjax(sysTodoService.insertTodo(content));
    }

    @Log(title = "备忘录", businessType = BusinessType.UPDATE)
    @ApiOperation("修改备忘")
    @PutMapping
    public R edit(@Validated @RequestBody SysTodo sysTodo) {
        return toAjax(sysTodoService.updateTodo(sysTodo));
    }

    /**
     * 删除备忘
     */
    @Log(title = "备忘录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public R remove(@PathVariable String id) {
        sysTodoService.removeById(id);
        return R.ok();
    }
}

