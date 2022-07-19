package com.jzj.vblog.web.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.utils.result.ResultCode;
import com.jzj.vblog.utils.sign.PageUtils;
import com.jzj.vblog.web.pojo.page.TableDataInfo;

import java.util.List;

/**
 * @Author Jzj
 * @Date 2022/7/11 14:30
 * @Version 1.0
 * @Message: web层通用数据处理
 */
public class BaseController {

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageUtils.startPage();
    }


    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(ResultCode.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected  R toAjax(int rows)
    {
        return rows > 0 ? R.ok() : R.error();
    }

    /**
     * 返回成功
     */
    public R success()
    {
        return R.ok();
    }

    /**
     * 返回失败消息
     */
    public R error()
    {
        return R.error();
    }
}
