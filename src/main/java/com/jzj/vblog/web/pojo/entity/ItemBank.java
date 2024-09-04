package com.jzj.vblog.web.pojo.entity;

import com.jzj.vblog.web.pojo.entity.base.CommonEntity;
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
 * @since 2023-06-07 11:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ItemBank对象", description = "题库")
public class ItemBank extends CommonEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题目")
    private String title;

    @ApiModelProperty(value = "答案")
    private String answer;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "刷新率")
    private Integer refreshRate;
}
