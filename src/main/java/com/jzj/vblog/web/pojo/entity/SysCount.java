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

import java.util.Date;

/**
 * <p>
 * 统计归档
 * </p>
 *
 * @author Jzj
 * @since 2022-12-07 11:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysCount对象", description="统计归档")
public class SysCount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "文章总数")
    private Long articleCount;

    @ApiModelProperty(value = "归档总数")
    private Long summaryCount;

    @ApiModelProperty(value = "用户总数")
    private Long userCount;

    @ApiModelProperty(value = "点击总数")
    private Long clickCount;

    @ApiModelProperty(value = "点赞总数")
    private Long likeCount;

    @ApiModelProperty(value = "创建日期")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "日点击数")
    private Long clickDayCount;

    @ApiModelProperty(value = "日点赞数")
    private Long likeDayCount;


}
