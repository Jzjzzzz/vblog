package com.jzj.vblog.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 题库
 * </p>
 *
 * @author Jzj
 * @since 2023-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ItemBank对象", description = "题库")
public class ItemBank extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "题目")
    private String title;

    @ApiModelProperty(value = "答案")
    private String answer;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "刷新率")
    private Integer refreshRate;

    @ApiModelProperty(value = "状态")
    private String status;


}
