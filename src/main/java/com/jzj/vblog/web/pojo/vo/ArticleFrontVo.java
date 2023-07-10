package com.jzj.vblog.web.pojo.vo;

import com.jzj.vblog.web.pojo.entity.SysDictData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author Jzj
 * @Date 2022/9/6 16:00
 * @Version 1.0
 * @Message: 前台文章详细vo
 */
@Data
@ApiModel(description = "前台文章详细vo")
public class ArticleFrontVo {
    @ApiModelProperty(value = "文章id")
    private String id;

    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "文章分类")
    private String articleType;

    @ApiModelProperty(value = "标签列表")
    private List<SysDictData> tagList;

    @ApiModelProperty(value = "作者")
    private String createBy;

    @ApiModelProperty(value = "是否开启评论")
    private String commentStatus;

    @ApiModelProperty(value = "文章内容转html")
    private String htmlContent;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "修改时间")
    private String updateTime;

    @ApiModelProperty(value = "归档id")
    private String aggregateId;

    @ApiModelProperty(value = "评论数")
    private Integer commentNumber;

    @ApiModelProperty(value = "点赞数")
    private Long numberLike;

    @ApiModelProperty(value = "Pre,Next列表")
    private List<ArticleNextPreData> articleNextPreDataList;

    @ApiModelProperty(value = "精品文章列表")
    private List<ArticlePopular> articlePopularList;


}
