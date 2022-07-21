package com.jzj.vblog.web.pojo.entity;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Jzj
 * @Date 2022/7/8 16:23
 * @Version 1.0
 * @Message: Entity基类
 */

@ApiModel(value="Entity基类", description="Entity基类")
public class BaseEntity implements Serializable {

    /** 搜索值 */
    private String searchValue;

    /** 请求参数 */
    private Map<String, Object> params;

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }
}
