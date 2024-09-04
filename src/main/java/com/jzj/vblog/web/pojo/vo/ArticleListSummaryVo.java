package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 文章列表归档使用vo
 * </p>
 *
 * @author Jzj
 * @since 2022/9/6 16:00
 */
@Data
@ApiModel(description = "文章列表归档使用vo")
public class ArticleListSummaryVo {

    @ApiModelProperty(value = "文章id")
    private String key;

    @ApiModelProperty(value = "文章标题")
    private String label;

    @ApiModelProperty(value = "是否属于当前归档(0-不属于,1-属于)")
    private Integer belong = 0;
}
