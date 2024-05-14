package com.jzj.vblog.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jzj.vblog.web.pojo.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 字典数据表
 * </p>
 *
 * @author Jzj
 * @since 2022-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysDictData对象", description = "字典数据表")
public class SysDictData extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "字典排序")
    private Integer dictSort;

    @ApiModelProperty(value = "字典标签")
    private String dictLabel;

    @ApiModelProperty(value = "字典键值")
    private String dictValue;

    @ApiModelProperty(value = "字典类型")
    private String dictType;

    @ApiModelProperty(value = "样式属性（其他样式扩展）")
    private String cssClass;

    @ApiModelProperty(value = "表格回显样式")
    private String listClass;

    @ApiModelProperty(value = "是否默认（Y是 N否）")
    private String isDefault;

    @ApiModelProperty(value = "状态（0正常 1停用）")
    private String status;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
