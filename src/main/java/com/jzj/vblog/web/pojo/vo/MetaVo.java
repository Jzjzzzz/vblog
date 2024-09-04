package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 路由显示信息vo
 * </p>
 *
 * @author Jzj
 * @since 2022/7/22 11:12
 */
@Data
@ApiModel(description = "路由显示信息vo")
public class MetaVo {
    @ApiModelProperty(value = "设置该路由在侧边栏和面包屑中展示的名字")
    private String title;

    @ApiModelProperty(value = "设置该路由的图标，对应路径src/assets/icons/svg")
    private String icon;

    public MetaVo() {
    }

    public MetaVo(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

}

