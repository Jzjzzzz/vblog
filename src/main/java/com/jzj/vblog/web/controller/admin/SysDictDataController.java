package com.jzj.vblog.web.controller.admin;


import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.utils.sign.StringUtils;
import com.jzj.vblog.web.pojo.entity.SysDictData;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.service.SysDictDataService;
import com.jzj.vblog.web.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public TableDataInfo list(SysDictData dictData)
    {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public R dictType(@PathVariable String dictType)
    {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data))
        {
            data = new ArrayList<SysDictData>();
        }
        return R.ok(data);
    }

    /**
     * 查询字典数据详细
     */
    @GetMapping(value = "/{dictCode}")
    public R getInfo(@PathVariable Long dictCode)
    {
        return R.ok(dictDataService.selectDictDataById(dictCode));
    }

}

