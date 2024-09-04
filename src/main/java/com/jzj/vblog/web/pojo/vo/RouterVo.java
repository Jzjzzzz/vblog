package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 路由配置信息vo
 * </p>
 *
 * @author Jzj
 * @since 2022/7/22 11:12
 */
@Data
@ApiModel(description = "路由配置信息vo")
public class RouterVo {

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现")
    private boolean hidden;

    @ApiModelProperty(value = "组件地址")
    private String component;

    @ApiModelProperty(value = "当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面")
    private Boolean alwaysShow;

    @ApiModelProperty(value = "其他元素")
    private MetaVo meta;

    @ApiModelProperty(value = "子路由")
    private List<RouterVo> children;

}
