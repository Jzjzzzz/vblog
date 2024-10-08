package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 前台文章详细头部内容vo
 * </p>
 *
 * @author Jzj
 * @since 2023/7/10 16:09
 */
@Data
@ApiModel(description = "前台文章详细头部内容vo")
public class ArticleHeadVo {

    @ApiModelProperty(value = "文章id")
    private String id;

    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "文章标签列表")
    private List<String> articleTagList;

    @ApiModelProperty(value = "文章标签")
    private String articleTag;

    @ApiModelProperty(value = "作者")
    private String createBy;

    @ApiModelProperty(value = "封面")
    private String logImg;

    @ApiModelProperty(value = "修改时间")
    private String updateTime;

    @ApiModelProperty(value = "访问量")
    private Long clickRate;

    @ApiModelProperty(value = "评论数")
    private Long commentNumber;

}
