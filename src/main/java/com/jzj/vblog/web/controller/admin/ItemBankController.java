package com.jzj.vblog.web.controller.admin;


import cn.dev33.satoken.annotation.SaCheckLogin;
import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.ItemBank;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.service.ItemBankService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 题库 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2023-06-07
 */
@RestController
@RequestMapping("/itemBank")
@SaCheckLogin
public class ItemBankController extends BaseController {
    @Autowired
    private ItemBankService itemBankService;

    @ApiOperation("分页列表")
    @GetMapping
    public TableDataInfo pageList(ItemBank itemBank) {
        startPage();
        List<ItemBank> list = itemBankService.pageList(itemBank);
        return getDataTable(list);
    }

    @Log(title = "题库管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增题库")
    @PostMapping
    public R add(@Validated @RequestBody ItemBank itemBank) {
        return toAjax(itemBankService.add(itemBank));
    }

    @ApiOperation("根据题库id获取题库详细信息")
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable String id) {
        return R.ok(itemBankService.selectById(id));
    }

    @ApiOperation("修改题库")
    @Log(title = "题库管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@Validated @RequestBody ItemBank itemBank) {
        return toAjax(itemBankService.modify(itemBank));
    }

    @ApiOperation("删除题库")
    @Log(title = "题库管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable List<String> ids, HttpServletRequest request) {
        return toAjax(itemBankService.deleteByIds(ids, request));
    }

    @ApiOperation("随机获取一条题目")
    @GetMapping("/random")
    public R random() {
        return R.ok(itemBankService.random());
    }
}

