package com.jzj.vblog.web.controller.admin;


import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.utils.sign.StringUtils;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.SysDictData;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.service.SysDictDataService;
import com.jzj.vblog.web.service.SysDictTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 字典数据表 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-07-08
 */
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {
    @Autowired
    private SysDictDataService dictDataService;

    @Autowired
    private SysDictTypeService dictTypeService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('btn.dict.list')")
    public TableDataInfo list(SysDictData dictData) {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    @ApiOperation("根据字典类型查询字典数据信息")
    @GetMapping(value = "/type/{dictType}")
    public R dictType(@PathVariable String dictType) {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data)) {
            data = new ArrayList<>();
        }
        return R.ok(data);
    }

    @ApiOperation("查询字典数据详细")
    @GetMapping(value = "/{dictCode}")
    public R getInfo(@PathVariable Long dictCode) {
        return R.ok(dictDataService.selectDictDataById(dictCode));
    }

    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @ApiOperation("新增字典类型")
    @PostMapping
    @PreAuthorize("hasAuthority('btn.dict.add')")
    public R add(@Validated @RequestBody SysDictData dict) {
        if (UserConstants.NOT_UNIQUE.equals(dictDataService.checkDictDataUnique(dict))) {
            return R.error("新增字典类型'" + dict.getDictValue() + "'失败，字典键值已存在");
        }
        dict.setCreateBy(getUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }

    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @ApiOperation("修改保存字典类型")
    @PutMapping
    @PreAuthorize("hasAuthority('btn.dict.edit')")
    public R edit(@Validated @RequestBody SysDictData dict) {
        if (UserConstants.NOT_UNIQUE.equals(dictDataService.checkDictDataUnique(dict))) {
            return R.error("修改字典类型'" + dict.getDictValue() + "'失败，字典键值已存在");
        }
        dict.setUpdateBy(getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @ApiOperation("删除字典类型")
    @PreAuthorize("hasAuthority('btn.dict.del')")
    @DeleteMapping("/{dictCodes}")
    public R remove(@PathVariable Long[] dictCodes) {
        dictDataService.deleteDictDataByIds(dictCodes);
        return success();
    }
}

