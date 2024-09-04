package com.jzj.vblog.web.pojo.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 分配菜单vo
 * </p>
 *
 * @author Jzj
 * @since 2022/9/6 16:03
 */
@ApiModel(description = "分配菜单")
@Data
public class AssignMenuVo {

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "菜单id列表")
    private List<String> menuIdList;

}
