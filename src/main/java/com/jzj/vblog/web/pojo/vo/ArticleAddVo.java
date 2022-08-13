package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Jzj
 * @Date 2022/8/4 10:36
 * @Version 1.0
 * @Message: 文章新增修改Vo
 */
@Data
@ApiModel(description = "文章新增修改vo")
public class ArticleAddVo {

    @ApiModelProperty(value = "文章id")
    private String id;

    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "文章状态")
    private String status;

    @ApiModelProperty(value = "文章分类")
    private String articleType;

    @ApiModelProperty(value = "文章标签")
    private String articleTag;

    @ApiModelProperty(value = "是否置顶")
    private String topStatus;

    @ApiModelProperty(value = "是否原创")
    private String originStatus;

    @ApiModelProperty(value = "是否开启评论")
    private String commentStatus;

    @ApiModelProperty(value = "封面")
    private String logImg;

    @ApiModelProperty(value = "简介")
    private String introduce;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "修改时间")
    private String updateTime;

    @ApiModelProperty(value = "访问量")
    private String clickRate;

    @ApiModelProperty(value = "归档id")
    private String aggregateId;
}
