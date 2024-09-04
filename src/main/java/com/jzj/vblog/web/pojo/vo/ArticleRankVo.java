package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 文章后台排行榜实体
 * </p>
 *
 * @author Jzj
 * @since 2022/9/6 16:03
 */
@Data
@ApiModel(description = "文章后台排行榜实体")
public class ArticleRankVo {

    @ApiModelProperty(value = "文章id")
    private String id;

    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "访问量")
    private Long clickRate;

    @ApiModelProperty(value = "点赞数")
    private Long numberLike;
}
