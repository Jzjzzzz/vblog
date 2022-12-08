package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author Jzj
 * @Date 2022/12/8 9:11
 * @Version 1.0
 * @Message: 获取七日统计vo
 */
@Data
public class BeforeDayCountVo {

    @ApiModelProperty(value = "日点击数列表")
    private List<Long> clickDayCounts;

    @ApiModelProperty(value = "日点赞数列表")
    private List<Long> likeDayCounts;

    @ApiModelProperty(value = "日期列表")
    private List<String> dateList;
}
