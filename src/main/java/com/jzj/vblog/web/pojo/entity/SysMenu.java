package com.jzj.vblog.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jzj.vblog.web.pojo.entity.base.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Jzj
 * @since 2024-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysMenu对象", description="菜单表")
public class SysMenu extends CommonEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "所属上级")
    private String parentId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型(0:目录,1:菜单,2:按钮)")
    private String type;

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "权限标识")
    private String perms;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer sortValue;

    @ApiModelProperty(value = "删除标记（0:不可用 1:可用）")
    private String isDeleted;

    // 下级列表
    @TableField(exist = false)
    private List<SysMenu> children;
    //是否选中
    @TableField(exist = false)
    private boolean isSelect;
}
