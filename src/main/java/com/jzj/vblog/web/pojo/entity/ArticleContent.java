package com.jzj.vblog.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jzj.vblog.web.pojo.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 文章内容
 * </p>
 *
 * @author Jzj
 * @since 2022-07-23 11:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ArticleContent对象", description = "文章内容")
public class ArticleContent extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章内容id")
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文章内容转html")
    private String htmlContent;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
