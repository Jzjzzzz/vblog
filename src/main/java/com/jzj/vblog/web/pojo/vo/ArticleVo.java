package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Jzj
 * @Date 2022/7/29 17:08
 * @Version 1.0
 * @Message: 前台文章vo
 */
@Data
@ApiModel(description = "前台文章vo")
public class ArticleVo {

    @ApiModelProperty(value = "文章详细id")
    private String id;

    @ApiModelProperty(value = "是否置顶")
    private Boolean isTop;

    @ApiModelProperty(value = "图片")
    private String logImg;

    @ApiModelProperty(value = "标签id")
    private String tagIds;

    @ApiModelProperty(value = "轮播图")
    private String[] banner;

    @ApiModelProperty(value = "是否热门")
    private Boolean isHot;

    @ApiModelProperty(value = "创建时间")
    private String pubTime;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "概要")
    private String summary;

    @ApiModelProperty(value = "点击数")
    private Integer viewsCount;

    @ApiModelProperty(value = "评论数")
    private Integer commentNumber;

    @ApiModelProperty(value = "分类名称")
    private String typeName;

    @ApiModelProperty(value = "标签名称数组")
    private String[] tagNameArray;

    @ApiModelProperty(value = "作者")
    private String createBy;

    @ApiModelProperty(value = "点赞数")
    private Long numberLike;
}
