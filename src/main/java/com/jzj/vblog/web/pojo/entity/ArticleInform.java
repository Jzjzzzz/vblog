package com.jzj.vblog.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jzj.vblog.web.pojo.entity.base.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 文章基础信息
 * </p>
 *
 * @author Jzj
 * @since 2022-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ArticleInform对象", description="文章基础信息")
public class ArticleInform extends CommonEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "简介")
    private String introduce;

    @ApiModelProperty(value = "标题图")
    private String logImg;

    @ApiModelProperty(value = "文章分类")
    private String articleType;

    @ApiModelProperty(value = "文章标签")
    private String articleTag;

    @ApiModelProperty(value = "是否置顶（0-不置顶，1-置顶）")
    private String topStatus;

    @ApiModelProperty(value = "是否原创（0-原创，1-转载）")
    private String originStatus;

    @ApiModelProperty(value = "是否开启评论（0-不开启，1-开启）")
    private String commentStatus;

    @ApiModelProperty(value = "文章点击量")
    private Long clickRate;

    @ApiModelProperty(value = "点赞数")
    private Long numberLike;

    @ApiModelProperty(value = "归档id")
    private String aggregateId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "评论数")
    private Integer commentNumber;

    @ApiModelProperty(value = "文章标签列表")
    @TableField(exist = false)
    private List<String>  articleTagList;
}
