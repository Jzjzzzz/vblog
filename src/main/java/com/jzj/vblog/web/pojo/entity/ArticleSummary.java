package com.jzj.vblog.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 文章归档
 * </p>
 *
 * @author Jzj
 * @since 2022-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ArticleSummary对象", description="文章归档")
public class ArticleSummary extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "归档表id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "归档名称")
    private String name;

    @ApiModelProperty(value = "归档图")
    private String banner;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "是否显示在首页")
    private String topStatus;

    @ApiModelProperty(value = "作者")
    private String author;


}
