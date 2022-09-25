package com.jzj.vblog.web.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Jzj
 * @Date 2022/9/6 16:00
 * @Version 1.0
 * @Message: 文章列表归档使用vo
 */

@Data
@ApiModel(description = "文章列表归档使用vo")
public class ArticleListSummaryVo {

    @TableId(value = "id")
    private String key;

    @ApiModelProperty(value = "文章标题")
    private String label;

    @ApiModelProperty(value = "是否属于当前归档(0-不属于,1-属于)")
    private Integer belong = 0;
}
