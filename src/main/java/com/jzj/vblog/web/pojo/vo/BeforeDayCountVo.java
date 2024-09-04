package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 获取七日统计vo
 * </p>
 *
 * @author Jzj
 * @since 2022/9/6 16:03
 */
@ApiModel(description = "获取七日统计vo")
@Data
public class BeforeDayCountVo {

    @ApiModelProperty(value = "日点击数列表")
    private List<Long> clickDayCounts;

    @ApiModelProperty(value = "日点赞数列表")
    private List<Long> likeDayCounts;

    @ApiModelProperty(value = "日期列表")
    private List<String> dateList;
}
