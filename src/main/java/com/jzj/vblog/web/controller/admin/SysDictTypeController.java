package com.jzj.vblog.web.controller.admin;


import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.pojo.entity.SysDictType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.service.SysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 字典类型表 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-07-08
 */
@Api(tags = "字典类型管理")
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController extends BaseController {
    @Autowired
    private SysDictTypeService dictTypeService;
    @ApiOperation("分页列表")
    @GetMapping("/list")
    public TableDataInfo list(SysDictType dictType)
    {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    /**
     * 查询字典类型详细
     */
    @GetMapping(value = "/{dictId}")
    public R getInfo(@PathVariable Long dictId)
    {
        return R.ok(dictTypeService.selectDictTypeById(dictId));
    }

    /**
     * 新增字典类型
     */
    @PostMapping
    public R add(@Validated @RequestBody SysDictType dict)
    {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict)))
        {
            return R.error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy("admin");
        return toAjax(dictTypeService.insertDictType(dict));
    }

    /**
     * 修改字典类型
     */
    @PutMapping
    public R edit(@Validated @RequestBody SysDictType dict)
    {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict)))
        {
            return R.error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setUpdateBy("admin");
        return toAjax(dictTypeService.updateDictType(dict));
    }

    /**
     * 删除字典类型
     */
    @DeleteMapping("/{dictIds}")
    public R remove(@PathVariable Long[] dictIds)
    {
        dictTypeService.deleteDictTypeByIds(dictIds);
        return success();
    }

    /**
     * 刷新字典缓存
     */

    @DeleteMapping("/refreshCache")
    public R refreshCache()
    {
        dictTypeService.resetDictCache();
        return R.ok();
    }

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionselect")
    public R optionselect()
    {
        List<SysDictType> dictTypes = dictTypeService.selectDictTypeAll();
        return R.ok(dictTypes);
    }

}

