package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 定时任务详情vo
 * </p>
 *
 * @author Jzj
 * @since 2022/9/6 16:03
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "定时任务详情vo")
public class JobVo {

    @ApiModelProperty(value = "定时任务全类名")
    private String jobClassName;

    @ApiModelProperty(value = "任务组名")
    private String jobGroupName;

    @ApiModelProperty(value = "定时任务cron表达式")
    private String cronExpression;
}
